package backend.challenge.model.mapper

import backend.challenge.database.entity.challenge.Award
import backend.challenge.model.dto.challenge.ChallengeRequest
import backend.challenge.model.dto.challenge.ChallengeResponse
import backend.challenge.service.SubmitService
import backend.shared.model.PageResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class AwardMapper(
    private val submitService: SubmitService
) {
    fun asEntity(request: ChallengeRequest.Award) = Award(
        name = request.name,
        description = request.description,
        weight = request.weight,
        team = request.team,
        visible = request.visible,
        start = request.start,
        end = request.end
    )

    fun updated(award: Award, request: ChallengeRequest.Award): Award {
        award.name = request.name
        award.description = request.description
        award.weight = request.weight
        award.team = request.team
        award.visible = request.visible
        return award
    }

    fun asResponse(award: Award, userId: UUID) = ChallengeResponse(
        id = award.id,
        name = award.name,
        description = award.description,
        weight = award.weight,
        team = award.team,
        visible = award.visible,
        start = award.start,
        end = award.end,
        solved = submitService.solved(award.id, userId),
    )

    fun asResponse(award: Award) = ChallengeResponse(
        id = award.id,
        name = award.name,
        description = award.description,
        weight = award.weight,
        team = award.team,
        visible = award.visible,
        start = award.start,
        end = award.end,
        solved = false
    )

    fun asPageResponse(items: Page<Award>, userId: UUID) = PageResponse.build(items) { asResponse(it, userId) }
}
