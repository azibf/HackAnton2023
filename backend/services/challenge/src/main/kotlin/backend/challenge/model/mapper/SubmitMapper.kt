package backend.challenge.model.mapper

import backend.challenge.database.entity.challenge.Award
import backend.challenge.database.entity.challenge.Challenge
import backend.challenge.database.entity.submit.AwardSubmit
import backend.challenge.database.entity.submit.ChallengeSubmit
import backend.challenge.model.dto.user.ProfileDto
import backend.challenge.model.exception.NoTeamException
import org.springframework.stereotype.Component

@Component
class SubmitMapper {
    fun asEntity(award: Award, user: ProfileDto): AwardSubmit {
        val team = if (award.team) user.team ?: throw NoTeamException() else null
        return AwardSubmit(
            award = award,
            userId = user.id,
            teamId = team?.id
        )
    }

    fun asEntity(challenge: Challenge, user: ProfileDto): ChallengeSubmit {
        val team = if (challenge.team) user.team ?: throw NoTeamException() else null
        return ChallengeSubmit(
            challenge = challenge,
            userId = user.id,
            teamId = team?.id
        )
    }
}
