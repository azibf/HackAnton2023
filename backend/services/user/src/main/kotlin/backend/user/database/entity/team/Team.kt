package backend.user.database.entity.team

import backend.shared.database.AbstractEntity
import backend.user.database.entity.user.User
import backend.user.util.UserConstant
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.PreRemove
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import org.springframework.data.jpa.repository.Modifying
import java.util.UUID

@Entity
class Team(

    @Column(nullable = false, unique = true, length = UserConstant.NAME_LENGTH)
    var name: String,

    ) : AbstractEntity() {

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    val invite: UUID = UUID.randomUUID()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    val participants: List<User> = emptyList()

    @PreRemove
    @Modifying
    fun destroyRelationships() {
        participants.forEach { it.team = null }
    }
}
