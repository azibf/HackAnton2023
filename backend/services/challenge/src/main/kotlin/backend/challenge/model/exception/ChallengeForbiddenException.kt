package backend.challenge.model.exception

import backend.shared.model.exception.AbstractApiException
import org.springframework.http.HttpStatus

class ChallengeForbiddenException : AbstractApiException(
    code = HttpStatus.FORBIDDEN,
    message = "exception.challenge.forbidden",
)
