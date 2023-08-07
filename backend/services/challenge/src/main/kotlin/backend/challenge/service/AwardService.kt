package backend.challenge.service

import backend.challenge.model.dto.challenge.ChallengeRequest
import backend.challenge.model.dto.challenge.ChallengeResponse
import backend.challenge.model.dto.challenge.SubmitRequest
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import java.util.UUID

interface AwardService {
    fun list(page: PageRequest, userId: UUID): PageResponse<ChallengeResponse>
    fun create(request: ChallengeRequest.Award): ChallengeResponse
    fun findById(id: UUID, userId: UUID): ChallengeResponse
    fun update(id: UUID, request: ChallengeRequest.Award): ChallengeResponse
    fun delete(id: UUID)
    fun submit(id: UUID, request: SubmitRequest.Award)
}
