package backend.user.model.dto.user

import backend.user.util.UserConstant
import jakarta.validation.constraints.Size

class UserRequest(
    @field:Size(max = UserConstant.NAME_LENGTH)
    val name: String,

    @field:Size(max = UserConstant.TOKEN_LENGTH)
    val token: String,
)
