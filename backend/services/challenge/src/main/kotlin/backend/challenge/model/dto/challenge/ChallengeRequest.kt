package backend.challenge.model.dto.challenge

import backend.challenge.controller.validator.challenge.ChallengeConstraint
import backend.challenge.util.ChallengeConstant
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@ChallengeConstraint
sealed class ChallengeRequest(
    @field:Size(max = ChallengeConstant.NAME_LENGTH)
    val name: String,

    @field:Size(max = ChallengeConstant.DESCRIPTION_LENGTH)
    val description: String?,

    val weight: Double,
    val team: Boolean,
    val visible: Boolean,

    val start: LocalDateTime?,
    val end: LocalDateTime?,
) {
    class Award(
        name: String,
        description: String?,
        weight: Double,
        team: Boolean,
        visible: Boolean,
        start: LocalDateTime?,
        end: LocalDateTime?,
    ) : ChallengeRequest(
        name = name,
        description = description,
        weight = weight,
        team = team,
        visible = visible,
        start = start,
        end = end,
    )

    class Challenge(
        name: String,
        description: String?,
        weight: Double,
        team: Boolean,
        visible: Boolean,
        start: LocalDateTime?,
        end: LocalDateTime?,

        @field:Size(max = ChallengeConstant.FLAG_LENGTH)
        val flag: String?,
    ) : ChallengeRequest(
        name = name,
        description = description,
        weight = weight,
        team = team,
        visible = visible,
        start = start,
        end = end,
    )
}
