package backend.shared.model.exception

import backend.shared.config.localization.Localization
import backend.shared.model.ApiResponse
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.function.ServerResponse

@JsonIgnoreProperties("cause", "stackTrace", "suppressed", "localizedMessage")
abstract class AbstractApiException(
    override var message: String,

    @JsonIgnore
    val code: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
) : ApiResponse, Exception() {
    override fun status() = code

    open fun asResponse(localization: Localization): ServerResponse {
        message = localization.i18n(message)
        return ServerResponse.status(status()).body(this)
    }
}
