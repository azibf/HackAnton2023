package backend.challenge.model.dto.user

import java.util.UUID

class ProfileDto(
    id: UUID,
    name: String,

    val team: TeamDto?,
) : UserDto(
    id = id,
    name = name,
)
