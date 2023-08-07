package backend.user.model.dto.team

import backend.shared.model.EntityResponse
import java.util.UUID

class TeamResponse(
    override val id: UUID,
    val name: String,
) : EntityResponse
