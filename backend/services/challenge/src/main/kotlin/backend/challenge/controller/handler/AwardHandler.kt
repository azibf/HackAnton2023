package backend.challenge.controller.handler

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

interface AwardHandler {
    fun list(serverRequest: ServerRequest): ServerResponse
    fun create(serverRequest: ServerRequest): ServerResponse
    fun findById(serverRequest: ServerRequest): ServerResponse
    fun update(serverRequest: ServerRequest): ServerResponse
    fun delete(serverRequest: ServerRequest): ServerResponse
    fun submit(serverRequest: ServerRequest): ServerResponse
}
