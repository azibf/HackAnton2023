package backend.shared.model.exception

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

class MalformedRequestException(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val reason: String? = null,
) : AbstractApiException(
    code = HttpStatus.BAD_REQUEST,
    message = "exception.request.malformed",
)
