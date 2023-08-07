package backend.broadcast.controller.handler.impl

import backend.broadcast.controller.handler.EventHandler
import backend.broadcast.model.dto.EventRequest
import backend.broadcast.service.EventService
import backend.shared.controller.AbstractHandler
import backend.shared.util.paramOrElse
import backend.shared.util.pathVariableOrThrow
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.time.LocalDate
import java.util.UUID

@Controller
class EventHandlerImpl(
    private val service: EventService,
) : AbstractHandler(), EventHandler {

    override fun list(serverRequest: ServerRequest): ServerResponse {
        val date = serverRequest.paramOrElse<LocalDate>("date") { LocalDate.now() }
        return service.list(date).asMessage()
    }

    override fun create(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.validated<EventRequest>()
        return service.create(request).asMessage("message.common.created")
    }

    override fun findById(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.findById(id).asMessage()
    }

    override fun update(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        val request = serverRequest.validated<EventRequest>()
        return service.update(id, request).asMessage("message.common.updated")
    }

    override fun delete(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.delete(id).asMessage("message.common.deleted")
    }
}
