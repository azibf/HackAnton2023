package backend.challenge.database.entity.submit

import backend.challenge.database.entity.challenge.Challenge
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity
class ChallengeSubmit(
    userId: UUID,
    teamId: UUID?,

    @ManyToOne(optional = false)
    val challenge: Challenge,
) : AbstractSubmit(
    userId = userId,
    teamId = teamId,
)
