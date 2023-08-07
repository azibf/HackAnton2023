package backend.challenge.model.mapper

import backend.challenge.database.entity.challenge.Challenge
import backend.challenge.model.dto.challenge.ChallengeRequest
import backend.challenge.model.dto.challenge.ChallengeResponse
import backend.challenge.service.SubmitService
import backend.shared.model.PageResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ChallengeMapper(
    private val submitService: SubmitService
) {
    fun asEntity(request: ChallengeRequest.Challenge) = Challenge(
        name = request.name,
        description = request.description,
        flag = request.flag,
        weight = request.weight,
        team = request.team,
        visible = request.visible,
        start = request.start,
        end = request.end,
    )

    fun updated(challenge: Challenge, request: ChallengeRequest.Challenge): Challenge {
        challenge.name = request.name
        challenge.description = request.description
        challenge.flag = request.flag
        challenge.weight = request.weight
        challenge.team = request.team
        challenge.visible = request.visible
        challenge.start = request.start
        challenge.end = request.end
        return challenge
    }

    fun asResponse(challenge: Challenge, userId: UUID) = ChallengeResponse(
        id = challenge.id,
        name = challenge.name,
        description = challenge.description,
        weight = challenge.weight,
        team = challenge.team,
        visible = challenge.visible,
        start = challenge.start,
        end = challenge.end,
        solved = submitService.solved(challenge.id, userId)
    )

    fun asResponse(challenge: Challenge) = ChallengeResponse(
        id = challenge.id,
        name = challenge.name,
        description = challenge.description,
        weight = challenge.weight,
        team = challenge.team,
        visible = challenge.visible,
        start = challenge.start,
        end = challenge.end,
        solved = false
    )

    fun asPageResponse(items: Page<Challenge>, userId: UUID) = PageResponse.build(items) { asResponse(it, userId) }
}
