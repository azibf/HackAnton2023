package backend.challenge.database.entity.challenge

import backend.challenge.util.ChallengeConstant
import backend.shared.database.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
sealed class AbstractChallenge(
    @Column(nullable = false, unique = true, length = ChallengeConstant.NAME_LENGTH)
    var name: String,

    @Column(length = ChallengeConstant.DESCRIPTION_LENGTH)
    var description: String?,

    @Column(nullable = false)
    var weight: Double,

    @Column(nullable = false)
    var team: Boolean,

    @Column(nullable = false)
    var visible: Boolean,

    @Column
    var start: LocalDateTime?,

    @Column
    var end: LocalDateTime?,

    ) : AbstractEntity() {
    fun available(): Boolean {
        val now = LocalDateTime.now()
        return visible && (start == null || now.isAfter(start)) && (end == null || now.isBefore(end))
    }
}
