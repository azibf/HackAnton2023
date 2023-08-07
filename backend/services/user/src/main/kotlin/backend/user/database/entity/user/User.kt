package backend.user.database.entity.user

import backend.shared.database.AbstractEntity
import backend.user.database.entity.team.Team
import backend.user.util.UserConstant
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Index
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "token")])
class User(

    @Column(nullable = false, unique = true, length = UserConstant.NAME_LENGTH)
    var name: String,

    @Column(nullable = false, unique = true, length = UserConstant.TOKEN_LENGTH)
    var token: String,

    @Column(nullable = false)
    val admin: Boolean = false,

    ) : AbstractEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team? = null

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "user")
    val telegrams: List<Telegram> = listOf()
}
