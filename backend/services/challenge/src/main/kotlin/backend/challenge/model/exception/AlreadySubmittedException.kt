package backend.challenge.model.exception

import backend.shared.model.exception.AbstractApiException
import org.springframework.http.HttpStatus

class AlreadySubmittedException : AbstractApiException(
    code = HttpStatus.CONFLICT,
    message = "exception.challenge.submitted",
)
