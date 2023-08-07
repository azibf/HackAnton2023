package backend.challenge.database.entity.challenge

import backend.challenge.database.entity.submit.ChallengeSubmit
import backend.challenge.util.ChallengeConstant
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import java.time.LocalDateTime

@Entity
class Challenge(
    name: String,
    description: String?,
    weight: Double,
    team: Boolean,
    visible: Boolean,
    start: LocalDateTime?,
    end: LocalDateTime?,

    @Column(length = ChallengeConstant.FLAG_LENGTH)
    var flag: String?,

    ) : AbstractChallenge(
    name = name,
    description = description,
    weight = weight,
    team = team,
    visible = visible,
    start = start,
    end = end,
) {

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "challenge")
    val submits: List<ChallengeSubmit> = emptyList()
}
