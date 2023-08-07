package backend.challenge.database.entity.challenge

import backend.challenge.database.entity.submit.AwardSubmit
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import java.time.LocalDateTime

@Entity
class Award(
    name: String,
    description: String?,
    weight: Double,
    team: Boolean,
    visible: Boolean,
    start: LocalDateTime?,
    end: LocalDateTime?,
) : AbstractChallenge(
    name = name,
    description = description,
    weight = weight,
    team = team,
    visible = visible,
    start = start,
    end = end,
) {

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "award")
    val submits: List<AwardSubmit> = emptyList()
}
