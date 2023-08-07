package backend.user.controller.handler.impl

import backend.shared.controller.AbstractHandler
import backend.shared.util.pathVariableOrThrow
import backend.user.controller.handler.UserHandler
import backend.user.model.dto.user.LinkTelegramRequest
import backend.user.model.dto.user.UnlinkTelegramRequest
import backend.user.model.dto.user.UserRequest
import backend.user.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.util.UUID

@Controller
class UserHandlerImpl(
    private val service: UserService,
) : AbstractHandler(), UserHandler {

    override fun list(serverRequest: ServerRequest): ServerResponse {
        val page = serverRequest.page()
        return service.list(page).asMessage()
    }

    override fun listById(serverRequest: ServerRequest): ServerResponse {
        val ids = serverRequest.validated<List<UUID>>()
        return service.listById(ids).asMessage()
    }

    override fun create(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.validated<UserRequest>()
        return service.create(request).asMessage("message.common.created")
    }

    override fun findById(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.findById(id).asMessage()
    }

    override fun update(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        val request = serverRequest.validated<UserRequest>()
        return service.update(id, request).asMessage("message.common.updated")
    }

    override fun delete(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.delete(id).asMessage("message.common.deleted")
    }

    override fun linkTelegram(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.validated<LinkTelegramRequest>()
        return service.linkTelegram(request).asMessage("message.telegram.linked")
    }

    override fun unlinkTelegram(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.validated<UnlinkTelegramRequest>()
        return service.unlinkTelegram(request).asMessage("message.telegram.unlinked")
    }

    override fun findByChatId(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<String>("id")
        return service.findByChatId(id).asMessage()
    }

    override fun findByToken(serverRequest: ServerRequest): ServerResponse {
        val token = serverRequest.pathVariableOrThrow<String>("token")
        return service.findByToken(token).asMessage()
    }
}
