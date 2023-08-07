package backend.broadcast.model.mapper

import backend.broadcast.database.entity.Broadcast
import backend.broadcast.model.dto.BroadcastRequest
import backend.broadcast.model.dto.BroadcastResponse
import backend.shared.model.PageResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class BroadcastMapper {
    fun asEntity(request: BroadcastRequest) = Broadcast(
        title = request.title,
        description = request.description,
        start = request.start,
    )

    fun update(broadcast: Broadcast, request: BroadcastRequest): Broadcast {
        broadcast.title = request.title
        broadcast.description = request.description
        broadcast.start = request.start
        return broadcast
    }

    fun asResponse(broadcast: Broadcast) = BroadcastResponse(
        id = broadcast.id,
        title = broadcast.title,
        description = broadcast.description,
        start = broadcast.start,
    )

    fun asPageResponse(items: Page<Broadcast>) = PageResponse.build(items, ::asResponse)
}
