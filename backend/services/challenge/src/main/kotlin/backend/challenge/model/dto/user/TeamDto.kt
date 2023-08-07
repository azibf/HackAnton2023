package backend.challenge.model.dto.user

import backend.shared.model.EntityResponse
import java.util.UUID

class TeamDto(
    override val id: UUID,
    val name: String,
) : EntityResponse
