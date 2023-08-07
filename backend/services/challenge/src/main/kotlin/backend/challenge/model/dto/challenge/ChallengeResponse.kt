package backend.challenge.model.dto.challenge

import backend.shared.model.EntityResponse
import java.time.LocalDateTime
import java.util.UUID

class ChallengeResponse(
    override val id: UUID,
    val name: String,
    val description: String?,
    val weight: Double,
    val team: Boolean,
    val visible: Boolean,
    val solved: Boolean,
    val start: LocalDateTime?,
    val end: LocalDateTime?,
) : EntityResponse
