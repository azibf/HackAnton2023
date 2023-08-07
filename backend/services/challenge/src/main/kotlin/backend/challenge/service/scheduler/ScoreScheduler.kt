package backend.challenge.service.scheduler

import backend.challenge.database.dao.score.TeamScoreDao
import backend.challenge.database.dao.score.UserScoreDao
import backend.challenge.database.dao.submit.AwardSubmitDao
import backend.challenge.database.dao.submit.ChallengeSubmitDao
import backend.challenge.database.entity.score.TeamScore
import backend.challenge.database.entity.score.UserScore
import backend.challenge.database.entity.submit.AbstractSubmit
import backend.challenge.database.entity.submit.AwardSubmit
import backend.challenge.database.entity.submit.ChallengeSubmit
import backend.challenge.model.mapper.ScoreMapper
import backend.challenge.service.feign.TeamService
import backend.shared.config.logging.Logger
import org.springframework.data.jpa.repository.Modifying
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class ScoreScheduler(
    private val challengeSubmitDao: ChallengeSubmitDao,
    private val awardSubmitDao: AwardSubmitDao,
    private val userScoreDao: UserScoreDao,
    private val teamScoreDao: TeamScoreDao,
    private val logger: Logger,
    private val mapper: ScoreMapper,
    private val teamService: TeamService,
) {
    @Modifying
    @Transactional
    @Scheduled(fixedDelay = 60 * 1000L) // Every 1 minute
    fun updateScores() {
        logger.info("Start updating scores")
        updateUserScores()
        updateTeamScores()
        logger.info("Updating scores has been completed")
    }

    fun updateUserScores() {
        userScoreDao.deleteAll()
        val submits = challengeSubmitDao.findByTeamIdNull() + awardSubmitDao.findByTeamIdNull()
        val scores = submits
            .map { it.userId }.distinct()
            .associateWith { findOrCreateUserScore(it) }
        submits.forEach { submit -> updateUserScore(submit, scores) }
    }

    fun updateUserScore(submit: AbstractSubmit, scores: Map<UUID, UserScore>) {
        val score = scores[submit.userId] ?: return
        score.score += when (submit) {
            is ChallengeSubmit -> submit.challenge.weight
            is AwardSubmit -> submit.award.weight
        }
    }

    fun updateTeamScores() {
        teamScoreDao.deleteAll()
        val submits = challengeSubmitDao.findByTeamIdNotNull() + awardSubmitDao.findByTeamIdNotNull()
        val scores = submits
            .mapNotNull { it.teamId }.distinct()
            .associateWith { findOrCreateTeamScore(it) }
        submits.forEach { submit -> updateTeamScore(submit, scores) }
    }

    fun updateTeamScore(submit: AbstractSubmit, scores: Map<UUID, TeamScore>) {
        val teamId = submit.teamId ?: return
        val score = scores[teamId] ?: return
        val weight = when (submit) {
            is ChallengeSubmit -> submit.challenge.weight
            is AwardSubmit -> submit.award.weight
        }
        score.score += weight
        val participants = teamService.findParticipants(teamId).data.content
        participants.forEach { user ->
            val userScore = findOrCreateUserScore(user.id)
            userScore.score += weight / participants.size
        }
    }

    fun findOrCreateTeamScore(teamId: UUID): TeamScore {
        var score = teamScoreDao.findTeamScoreByTeamId(teamId)
        if (score == null) {
            val entity = mapper.asTeamScoreEntity(teamId)
            score = teamScoreDao.save(entity)
        }
        return score
    }

    fun findOrCreateUserScore(userId: UUID): UserScore {
        var score = userScoreDao.findUserScoreByUserId(userId)
        if (score == null) {
            val entity = mapper.asUserScoreEntity(userId)
            score = userScoreDao.save(entity)
        }
        return score
    }
}
