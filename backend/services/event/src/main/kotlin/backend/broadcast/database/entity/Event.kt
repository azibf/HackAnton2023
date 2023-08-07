package backend.broadcast.database.entity

import backend.broadcast.util.EventConstant
import backend.shared.database.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDateTime

@Entity
class Event(

    @Column(nullable = false, length = EventConstant.TITLE_LENGTH)
    var title: String,

    @Column(length = EventConstant.DESCRIPTION_LENGTH)
    var description: String?,

    @Column(length = EventConstant.NAME_LENGTH)
    var speaker: String?,

    @Column(length = EventConstant.NAME_LENGTH)
    var affiliate: String?,

    @Column(nullable = false)
    var start: LocalDateTime,

    @Column(nullable = false)
    var end: LocalDateTime,

    ) : AbstractEntity()
