package backend.user.controller.handler

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

interface UserHandler {
    fun list(serverRequest: ServerRequest): ServerResponse
    fun listById(serverRequest: ServerRequest): ServerResponse
    fun create(serverRequest: ServerRequest): ServerResponse
    fun findById(serverRequest: ServerRequest): ServerResponse
    fun update(serverRequest: ServerRequest): ServerResponse
    fun delete(serverRequest: ServerRequest): ServerResponse
    fun linkTelegram(serverRequest: ServerRequest): ServerResponse
    fun unlinkTelegram(serverRequest: ServerRequest): ServerResponse
    fun findByChatId(serverRequest: ServerRequest): ServerResponse
    fun findByToken(serverRequest: ServerRequest): ServerResponse
}
