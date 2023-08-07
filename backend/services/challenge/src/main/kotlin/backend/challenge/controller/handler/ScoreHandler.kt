package backend.challenge.controller.handler

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

interface ScoreHandler {
    fun userScoreboard(serverRequest: ServerRequest): ServerResponse
    fun teamScoreboard(serverRequest: ServerRequest): ServerResponse
    fun userScore(serverRequest: ServerRequest): ServerResponse
    fun teamScore(serverRequest: ServerRequest): ServerResponse
    fun profileScore(serverRequest: ServerRequest): ServerResponse
    fun userHistory(serverRequest: ServerRequest): ServerResponse
    fun teamHistory(serverRequest: ServerRequest): ServerResponse
}
