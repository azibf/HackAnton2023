package backend.broadcast.model.dto

import backend.broadcast.util.BroadcastConstant
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

class BroadcastRequest(
    @field:Size(max = BroadcastConstant.TITLE_LENGTH)
    val title: String,

    @field:Size(max = BroadcastConstant.DESCRIPTION_LENGTH)
    val description: String?,

    val start: LocalDateTime,
)
