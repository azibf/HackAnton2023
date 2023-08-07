package backend.user.model.dto.team

import backend.user.util.UserConstant
import jakarta.validation.constraints.Size

class TeamRequest(
    @field:Size(max = UserConstant.NAME_LENGTH)
    val name: String,
)
