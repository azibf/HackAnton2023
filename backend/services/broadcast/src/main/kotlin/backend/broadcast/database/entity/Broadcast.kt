package backend.broadcast.database.entity

import backend.broadcast.util.BroadcastConstant
import backend.shared.database.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDateTime

@Entity
class Broadcast(

    @Column(nullable = false, length = BroadcastConstant.TITLE_LENGTH)
    var title: String,

    @Column(length = BroadcastConstant.DESCRIPTION_LENGTH)
    var description: String?,

    @Column(nullable = false)
    var start: LocalDateTime,

    ) : AbstractEntity()
