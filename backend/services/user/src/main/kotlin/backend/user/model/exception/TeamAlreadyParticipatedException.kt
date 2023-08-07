package backend.user.model.exception

import backend.shared.model.exception.AbstractApiException
import org.springframework.http.HttpStatus

class TeamAlreadyParticipatedException : AbstractApiException(
    code = HttpStatus.CONFLICT,
    message = "exception.team.participated",
)
