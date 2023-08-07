package backend.challenge.controller.handler.impl

import backend.challenge.controller.handler.ScoreHandler
import backend.challenge.service.ScoreService
import backend.shared.controller.AbstractHandler
import backend.shared.util.pathVariableOrThrow
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.util.UUID

@Controller
class ScoreHandlerImpl(
    private val service: ScoreService,
) : AbstractHandler(), ScoreHandler {

    override fun userScoreboard(serverRequest: ServerRequest): ServerResponse {
        val page = serverRequest.page(defaultField = "score", defaultOrder = Sort.Direction.DESC)
        return service.userScoreboard(page).asMessage()
    }

    override fun teamScoreboard(serverRequest: ServerRequest): ServerResponse {
        val page = serverRequest.page(defaultField = "score", defaultOrder = Sort.Direction.DESC)
        return service.teamScoreboard(page).asMessage()
    }

    override fun userScore(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.userScore(id).asMessage()
    }

    override fun teamScore(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.teamScore(id).asMessage()
    }

    override fun profileScore(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        return service.profileScore(id).asMessage()
    }

    override fun userHistory(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        val page = serverRequest.page()
        return service.userHistory(id, page).asMessage()
    }

    override fun teamHistory(serverRequest: ServerRequest): ServerResponse {
        val id = serverRequest.pathVariableOrThrow<UUID>("id")
        val page = serverRequest.page()
        return service.teamHistory(id, page).asMessage()
    }
}
