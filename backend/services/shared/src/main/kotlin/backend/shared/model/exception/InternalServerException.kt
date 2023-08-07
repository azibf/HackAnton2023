package backend.shared.model.exception

import org.springframework.http.HttpStatus

class InternalServerException : AbstractApiException(
    code = HttpStatus.INTERNAL_SERVER_ERROR,
    message = "exception.common.internal",
)
