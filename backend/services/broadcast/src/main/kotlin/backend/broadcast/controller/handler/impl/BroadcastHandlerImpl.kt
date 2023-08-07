package backend.broadcast.controller.handler.impl

import backend.broadcast.controller.handler.BroadcastHandler
import backend.broadcast.model.dto.BroadcastRequest
import backend.broadcast.service.BroadcastService
import backend.shared.controller.AbstractHandler
import backend.shared.util.pathVariableOrThrow
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.util.UUID

@Controller
class BroadcastHandlerImpl(
    private val service: BroadcastService,
) : AbstractHandler(), BroadcastHandler {

    override fun list(serverRequest: ServerRequest): ServerResponse {
        val page = serverRequest.page()
        return service.list(page).asMessage()
    }

    override fun create(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.validated<BroadcastRequest>()
        return service.create(request).asMessage("message.common.created")
    }

    override fun findById(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.findById(id).asMessage()
    }

    override fun update(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        val request = serverRequest.validated<BroadcastRequest>()
        return service.update(id, request).asMessage("message.common.updated")
    }

    override fun delete(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.delete(id).asMessage("message.common.deleted")
    }
}
