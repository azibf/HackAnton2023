package backend.challenge.controller.handler.impl

import backend.challenge.controller.handler.AwardHandler
import backend.challenge.model.dto.challenge.ChallengeRequest
import backend.challenge.model.dto.challenge.SubmitRequest
import backend.challenge.service.AwardService
import backend.shared.controller.AbstractHandler
import backend.shared.util.paramOrThrow
import backend.shared.util.pathVariableOrThrow
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.util.UUID

@Controller
class AwardHandlerImpl(
    private val service: AwardService,
) : AbstractHandler(), AwardHandler {

    override fun list(serverRequest: ServerRequest): ServerResponse {
        val page = serverRequest.page()
        val userId = serverRequest.paramOrThrow<UUID>("userId")
        return service.list(page, userId).asMessage()
    }

    override fun create(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.validated<ChallengeRequest.Award>()
        return service.create(request).asMessage("message.common.created")
    }

    override fun findById(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        val userId = serverRequest.paramOrThrow<UUID>("userId")
        return service.findById(id, userId).asMessage()
    }

    override fun update(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        val request = serverRequest.validated<ChallengeRequest.Award>()
        return service.update(id, request).asMessage("message.common.updated")
    }

    override fun delete(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.delete(id).asMessage("message.common.deleted")
    }

    override fun submit(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        val request = serverRequest.validated<SubmitRequest.Award>()
        return service.submit(id, request).asMessage("message.challenge.submitted")
    }
}
