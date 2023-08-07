package backend.shared.model.exception

import org.springframework.http.HttpStatus

class ForbiddenException : AbstractApiException(
    code = HttpStatus.FORBIDDEN,
    message = "exception.common.forbidden",
)
