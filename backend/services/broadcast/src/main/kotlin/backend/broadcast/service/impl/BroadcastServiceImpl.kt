package backend.broadcast.service.impl

import backend.broadcast.database.dao.BroadcastDao
import backend.broadcast.model.dto.BroadcastRequest
import backend.broadcast.model.dto.BroadcastResponse
import backend.broadcast.model.mapper.BroadcastMapper
import backend.broadcast.service.BroadcastService
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import backend.shared.model.exception.ResourceNotFoundException
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class BroadcastServiceImpl(
    private val broadcastDao: BroadcastDao,
    private val mapper: BroadcastMapper,
) : BroadcastService {

    override fun list(page: PageRequest): PageResponse<BroadcastResponse> {
        val events = broadcastDao.findAll(page.pageable)
        return mapper.asPageResponse(events)
    }

    override fun create(request: BroadcastRequest): BroadcastResponse {
        val entity = mapper.asEntity(request)
        val event = broadcastDao.save(entity)
        return mapper.asResponse(event)
    }

    override fun findById(id: UUID): BroadcastResponse {
        val event = broadcastDao.findEntityById(id) ?: throw ResourceNotFoundException()
        return mapper.asResponse(event)
    }

    @Modifying
    @Transactional
    override fun update(id: UUID, request: BroadcastRequest): BroadcastResponse {
        val event = broadcastDao.findEntityById(id) ?: throw ResourceNotFoundException()
        val updated = mapper.update(event, request)
        return mapper.asResponse(updated)
    }

    @Modifying
    @Transactional
    override fun delete(id: UUID) {
        val event = broadcastDao.findEntityById(id) ?: throw ResourceNotFoundException()
        broadcastDao.delete(event)
    }
}
