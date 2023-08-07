package backend.broadcast.service.impl

import backend.broadcast.database.dao.EventDao
import backend.broadcast.model.dto.EventRequest
import backend.broadcast.model.dto.EventResponse
import backend.broadcast.model.mapper.EventMapper
import backend.broadcast.service.EventService
import backend.shared.model.ListResponse
import backend.shared.model.exception.ResourceNotFoundException
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Service
class EventServiceImpl(
    private val eventDao: EventDao,
    private val mapper: EventMapper,
) : EventService {

    override fun list(date: LocalDate): ListResponse<EventResponse> {
        val start = date.atStartOfDay().let { LocalDateTime.from(it) }
        val end = start.plusDays(1)
        val events = eventDao.findByStartAfterAndEndBeforeOrderByStart(start, end)
        return mapper.asListResponse(events)
    }

    override fun create(request: EventRequest): EventResponse {
        val entity = mapper.asEntity(request)
        val event = eventDao.save(entity)
        return mapper.asResponse(event)
    }

    override fun findById(id: UUID): EventResponse {
        val event = eventDao.findEntityById(id) ?: throw ResourceNotFoundException()
        return mapper.asResponse(event)
    }

    @Modifying
    @Transactional
    override fun update(id: UUID, request: EventRequest): EventResponse {
        val event = eventDao.findEntityById(id) ?: throw ResourceNotFoundException()
        val updated = mapper.update(event, request)
        return mapper.asResponse(updated)
    }

    @Modifying
    @Transactional
    override fun delete(id: UUID) {
        val event = eventDao.findEntityById(id) ?: throw ResourceNotFoundException()
        eventDao.delete(event)
    }
}
