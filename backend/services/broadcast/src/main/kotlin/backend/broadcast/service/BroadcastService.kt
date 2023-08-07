package backend.broadcast.service

import backend.broadcast.model.dto.BroadcastRequest
import backend.broadcast.model.dto.BroadcastResponse
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import java.util.UUID

interface BroadcastService {
    fun list(page: PageRequest): PageResponse<BroadcastResponse>
    fun create(request: BroadcastRequest): BroadcastResponse
    fun findById(id: UUID): BroadcastResponse
    fun update(id: UUID, request: BroadcastRequest): BroadcastResponse
    fun delete(id: UUID)
}
