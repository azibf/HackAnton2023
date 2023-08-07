package backend.challenge.database.entity.submit

import backend.challenge.database.entity.challenge.Award
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity
class AwardSubmit(
    userId: UUID,
    teamId: UUID?,

    @ManyToOne(optional = false)
    val award: Award,
) : AbstractSubmit(
    userId = userId,
    teamId = teamId,
)
