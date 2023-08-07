package backend.user.model.dto.user

import backend.user.util.UserConstant
import jakarta.validation.constraints.Size

class LinkTelegramRequest(
    val token: String,

    @field:Size(max = UserConstant.TELEGRAM_ID_LENGTH)
    val telegramId: String,

    @field:Size(max = UserConstant.TELEGRAM_ID_LENGTH)
    val chatId: String,
)
