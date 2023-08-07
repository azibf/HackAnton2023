package backend.challenge.service

import backend.challenge.model.dto.challenge.SubmitRequest
import java.util.UUID

interface SubmitService {
    fun solved(challengeId: UUID, userId: UUID): Boolean
    fun submit(awardId: UUID, request: SubmitRequest.Award)
    fun submit(challengeId: UUID, request: SubmitRequest.Challenge)
}
