package backend.challenge.database.entity.submit

import backend.shared.database.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.UUID

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
sealed class AbstractSubmit(

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false)
    val userId: UUID,

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column
    var teamId: UUID?

) : AbstractEntity()
