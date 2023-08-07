package backend.challenge.database.entity.score

import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.UUID

@Entity
class UserScore(

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false)
    val userId: UUID,

    ) : AbstractScore()
