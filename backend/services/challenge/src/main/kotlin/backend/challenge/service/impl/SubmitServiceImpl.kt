package backend.challenge.service.impl

import backend.challenge.database.dao.challenge.AwardDao
import backend.challenge.database.dao.challenge.ChallengeDao
import backend.challenge.database.dao.submit.AwardSubmitDao
import backend.challenge.database.dao.submit.ChallengeSubmitDao
import backend.challenge.database.entity.challenge.AbstractChallenge
import backend.challenge.model.dto.challenge.SubmitRequest
import backend.challenge.model.dto.user.ProfileDto
import backend.challenge.model.dto.user.TeamDto
import backend.challenge.model.exception.AlreadySubmittedException
import backend.challenge.model.exception.ChallengeForbiddenException
import backend.challenge.model.exception.InvalidFlagException
import backend.challenge.model.mapper.SubmitMapper
import backend.challenge.service.SubmitService
import backend.challenge.service.feign.UserService
import backend.shared.model.exception.ResourceNotFoundException
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class SubmitServiceImpl(
    private val challengeDao: ChallengeDao,
    private val awardDao: AwardDao,
    private val challengeSubmitDao: ChallengeSubmitDao,
    private val awardSubmitDao: AwardSubmitDao,
    private val mapper: SubmitMapper,
    private val userService: UserService,
) : SubmitService {
    override fun solved(challengeId: UUID, userId: UUID): Boolean {
        val user = userService.findById(userId)
        if (challengeSubmitDao.existsByUserIdAndChallengeId(userId, challengeId)) {
            return true
        }
        val team = user.data.team ?: return false
        return challengeSubmitDao.existsByTeamIdAndChallengeId(team.id, challengeId)
    }

    @Modifying
    override fun submit(awardId: UUID, request: SubmitRequest.Award) {
        val award = awardDao.findEntityById(awardId) ?: throw ResourceNotFoundException()
        val user = userService.findByToken(request.token).data
        validate(award, user)
        val submit = mapper.asEntity(award, user)
        awardSubmitDao.save(submit)
    }

    @Modifying
    override fun submit(challengeId: UUID, request: SubmitRequest.Challenge) {
        val challenge = challengeDao.findEntityById(challengeId) ?: throw ResourceNotFoundException()
        if (challenge.flag != request.flag) throw InvalidFlagException()
        val user = userService.findById(request.userId).data
        validate(challenge, user)
        val submit = mapper.asEntity(challenge, user)
        challengeSubmitDao.save(submit)
    }

    private fun validate(challenge: AbstractChallenge, user: ProfileDto) {
        if (!challenge.available()) throw ChallengeForbiddenException()
        validateUserSubmitted(challenge, user)
        user.team?.let { validateTeamSubmitted(challenge, it) }
    }

    private fun validateUserSubmitted(challenge: AbstractChallenge, user: ProfileDto) {
        if (awardSubmitDao.existsByUserIdAndAwardId(user.id, challenge.id)) {
            throw AlreadySubmittedException()
        }
        if (challengeSubmitDao.existsByUserIdAndChallengeId(user.id, challenge.id)) {
            throw AlreadySubmittedException()
        }
    }

    private fun validateTeamSubmitted(challenge: AbstractChallenge, team: TeamDto) {
        if (awardSubmitDao.existsByTeamIdAndAwardId(team.id, challenge.id)) {
            throw AlreadySubmittedException()
        }
        if (challengeSubmitDao.existsByTeamIdAndChallengeId(team.id, challenge.id)) {
            throw AlreadySubmittedException()
        }
    }
}
