package backend.challenge.model.dto.score

import backend.challenge.model.dto.challenge.ChallengeResponse
import backend.shared.model.EntityResponse
import java.time.LocalDateTime
import java.util.UUID

class HistoryItemResponse(
    override val id: UUID,
    val submitted: LocalDateTime,
    val challenge: ChallengeResponse,
) : EntityResponse
