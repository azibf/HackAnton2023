package backend.broadcast.model.mapper

import backend.broadcast.database.entity.Event
import backend.broadcast.model.dto.EventRequest
import backend.broadcast.model.dto.EventResponse
import backend.shared.model.ListResponse
import org.springframework.stereotype.Component

@Component
class EventMapper {
    fun asEntity(request: EventRequest) = Event(
        title = request.title,
        speaker = request.speaker,
        affiliate = request.affiliate,
        description = request.description,
        start = request.start,
        end = request.end,
    )

    fun update(event: Event, request: EventRequest): Event {
        event.title = request.title
        event.description = request.description
        event.speaker = request.speaker
        event.affiliate = request.affiliate
        event.start = request.start
        event.end = request.end
        return event
    }

    fun asResponse(event: Event) = EventResponse(
        id = event.id,
        title = event.title,
        description = event.description,
        speaker = event.speaker,
        affiliate = event.affiliate,
        start = event.start,
        end = event.end,
    )

    fun asListResponse(items: List<Event>) = ListResponse.build(items, ::asResponse)
}
