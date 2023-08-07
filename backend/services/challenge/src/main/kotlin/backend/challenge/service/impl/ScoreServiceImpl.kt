package backend.challenge.service.impl

import backend.challenge.database.dao.score.TeamScoreDao
import backend.challenge.database.dao.score.UserScoreDao
import backend.challenge.database.dao.submit.ChallengeSubmitDao
import backend.challenge.model.dto.score.ProfileScoreResponse
import backend.challenge.model.dto.score.ScoreResponse
import backend.challenge.model.mapper.ScoreMapper
import backend.challenge.service.ScoreService
import backend.challenge.service.feign.TeamService
import backend.challenge.service.feign.UserService
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ScoreServiceImpl(
    private val challengeSubmitDao: ChallengeSubmitDao,
    private val userScoreDao: UserScoreDao,
    private val teamScoreDao: TeamScoreDao,
    private val mapper: ScoreMapper,
    private val userService: UserService,
    private val teamService: TeamService,
) : ScoreService {

    override fun userScoreboard(page: PageRequest): PageResponse<ScoreResponse.User> {
        val scores = userScoreDao.findAll(page.pageable)
        val ids = scores.content.map { it.userId }
        val users = userService.listById(ids).data.content
        return mapper.asUserScoreboard(scores, users)
    }

    override fun teamScoreboard(page: PageRequest): PageResponse<ScoreResponse.Team> {
        val scores = teamScoreDao.findAll(page.pageable)
        val ids = scores.content.map { it.teamId }
        val teams = teamService.listById(ids).data.content
        return mapper.asTeamScoreboard(scores, teams)
    }

    override fun userScore(id: UUID): ScoreResponse {
        val entity = userService.findById(id).data
        val score = userScoreDao.findUserScoreByUserId(id)?.score ?: .0
        return mapper.asResponse(entity, score)
    }

    override fun teamScore(id: UUID): ScoreResponse {
        val entity = teamService.findById(id).data
        val score = teamScoreDao.findTeamScoreByTeamId(id)?.score ?: .0
        return mapper.asResponse(entity, score)
    }

    override fun profileScore(id: UUID): ProfileScoreResponse {
        val entity = userService.findById(id).data
        val userScore = userScoreDao.findUserScoreByUserId(id)?.score ?: .0
        val teamScore = entity.team?.let { teamScoreDao.findTeamScoreByTeamId(it.id)?.score } ?: .0
        return mapper.asResponse(entity, userScore, teamScore)
    }

    override fun userHistory(id: UUID, page: PageRequest): ScoreResponse.History {
        val score = userScore(id)
        val history = challengeSubmitDao.findByUserId(id, page.pageable)
        return mapper.asHistoryResponse(score, history)
    }

    override fun teamHistory(id: UUID, page: PageRequest): ScoreResponse.History {
        val score = teamScore(id)
        val history = challengeSubmitDao.findByTeamId(id, page.pageable)
        return mapper.asHistoryResponse(score, history)
    }
}
