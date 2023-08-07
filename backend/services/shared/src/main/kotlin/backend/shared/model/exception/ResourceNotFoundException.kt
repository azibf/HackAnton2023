package backend.shared.model.exception

import org.springframework.http.HttpStatus

class ResourceNotFoundException : AbstractApiException(
    code = HttpStatus.NOT_FOUND,
    message = "exception.request.notFound",
)
