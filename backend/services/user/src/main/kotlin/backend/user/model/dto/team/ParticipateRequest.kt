package backend.user.model.dto.team

import java.util.UUID

class ParticipateRequest(
    val userId: UUID,
    val invite: UUID,
)
