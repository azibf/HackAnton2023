package backend.user.controller.handler.impl

import backend.shared.controller.AbstractHandler
import backend.shared.util.pathVariableOrThrow
import backend.user.controller.handler.TeamHandler
import backend.user.model.dto.team.ParticipateRequest
import backend.user.model.dto.team.TeamRequest
import backend.user.service.TeamService
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.util.UUID

@Controller
class TeamHandlerImpl(
    private val service: TeamService,
) : AbstractHandler(), TeamHandler {

    override fun list(serverRequest: ServerRequest): ServerResponse {
        val page = serverRequest.page()
        return service.list(page).asMessage()
    }

    override fun listById(serverRequest: ServerRequest): ServerResponse {
        val ids = serverRequest.validated<List<UUID>>()
        return service.listById(ids).asMessage()
    }

    override fun create(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.validated<TeamRequest>()
        return service.create(request).asMessage("message.common.created")
    }

    override fun findById(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.findById(id).asMessage()
    }

    override fun update(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        val request = serverRequest.validated<TeamRequest>()
        return service.update(id, request).asMessage("message.common.updated")
    }

    override fun delete(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.delete(id).asMessage("message.common.deleted")
    }

    override fun participate(serverRequest: ServerRequest): ServerResponse {
        val teamId = serverRequest.pathVariableOrThrow<UUID>("id")
        val request = serverRequest.validated<ParticipateRequest>()
        return service.participate(teamId, request).asMessage("message.team.joined")
    }

    override fun listParticipants(serverRequest: ServerRequest): ServerResponse {
        val teamId = serverRequest.pathVariableOrThrow<UUID>("id")
        val page = serverRequest.page()
        return service.listParticipants(teamId, page).asMessage()
    }
}
