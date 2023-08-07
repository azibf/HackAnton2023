package backend.user.model.dto.user

import backend.shared.model.EntityResponse
import backend.user.model.dto.team.TeamResponse
import java.util.UUID

class UserResponse(
    override val id: UUID,
    val name: String,
    val admin: Boolean,
    val team: TeamResponse?,
) : EntityResponse
