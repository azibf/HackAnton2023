package backend.broadcast.model.dto

import backend.broadcast.controller.validator.event.EventConstraint
import backend.broadcast.util.EventConstant
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@EventConstraint
class EventRequest(
    @field:Size(max = EventConstant.TITLE_LENGTH)
    val title: String,

    @field:Size(max = EventConstant.DESCRIPTION_LENGTH)
    val description: String?,

    @field:Size(max = EventConstant.NAME_LENGTH)
    val speaker: String?,

    @field:Size(max = EventConstant.NAME_LENGTH)
    val affiliate: String?,

    val start: LocalDateTime,
    val end: LocalDateTime,
)
