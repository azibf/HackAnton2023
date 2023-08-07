package backend.challenge.model.dto.challenge

import java.util.UUID

sealed class SubmitRequest {
    class Challenge(
        val flag: String,
        val userId: UUID,
    ) : SubmitRequest()

    class Award(
        val token: String
    )
}
