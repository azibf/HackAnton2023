package backend.shared.model

import org.springframework.http.HttpStatus
import org.springframework.web.servlet.function.ServerResponse

interface ApiResponse {
    fun status() = HttpStatus.OK
    fun asResponse() = ServerResponse.status(status()).body(this)
}
