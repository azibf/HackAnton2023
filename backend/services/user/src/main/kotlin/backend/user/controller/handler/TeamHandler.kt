package backend.user.controller.handler

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

interface TeamHandler {
    fun list(serverRequest: ServerRequest): ServerResponse
    fun listById(serverRequest: ServerRequest): ServerResponse
    fun create(serverRequest: ServerRequest): ServerResponse
    fun findById(serverRequest: ServerRequest): ServerResponse
    fun update(serverRequest: ServerRequest): ServerResponse
    fun delete(serverRequest: ServerRequest): ServerResponse
    fun participate(serverRequest: ServerRequest): ServerResponse
    fun listParticipants(serverRequest: ServerRequest): ServerResponse
}
