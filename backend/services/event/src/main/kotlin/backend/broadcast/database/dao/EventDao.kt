package backend.broadcast.database.dao

import backend.broadcast.database.entity.Event
import backend.shared.database.AbstractDao
import java.time.LocalDateTime

interface EventDao : AbstractDao<Event> {
    fun findByStartAfterAndEndBeforeOrderByStart(start: LocalDateTime, end: LocalDateTime): List<Event>
}
