package backend.challenge.model.exception

import backend.shared.model.exception.AbstractApiException
import org.springframework.http.HttpStatus

class NoTeamException : AbstractApiException(
    code = HttpStatus.BAD_REQUEST,
    message = "exception.challenge.teamRequired",
)
