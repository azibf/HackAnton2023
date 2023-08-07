package backend.user.model.exception

import backend.shared.model.exception.AbstractApiException
import org.springframework.http.HttpStatus

class TokenAlreadyExistsException : AbstractApiException(
    code = HttpStatus.CONFLICT,
    message = "exception.user.tokenExists",
)
