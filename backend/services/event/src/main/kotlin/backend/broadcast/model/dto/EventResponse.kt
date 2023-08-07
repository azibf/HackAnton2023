package backend.broadcast.model.dto

import backend.shared.model.EntityResponse
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.UUID

class EventResponse(
    override val id: UUID,
    val title: String,
    val description: String?,
    val speaker: String?,
    val affiliate: String?,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val start: LocalDateTime,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val end: LocalDateTime,
) : EntityResponse
