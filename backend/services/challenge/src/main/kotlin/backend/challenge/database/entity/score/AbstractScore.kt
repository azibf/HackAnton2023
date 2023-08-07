package backend.challenge.database.entity.score

import backend.shared.database.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class AbstractScore : AbstractEntity() {
    @Column(nullable = false)
    var score: Double = .0
}
