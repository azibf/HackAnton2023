package backend.broadcast.service

import backend.broadcast.model.dto.EventRequest
import backend.broadcast.model.dto.EventResponse
import backend.shared.model.ListResponse
import java.time.LocalDate
import java.util.UUID

interface EventService {
    fun list(date: LocalDate): ListResponse<EventResponse>
    fun create(request: EventRequest): EventResponse
    fun findById(id: UUID): EventResponse
    fun update(id: UUID, request: EventRequest): EventResponse
    fun delete(id: UUID)
}
