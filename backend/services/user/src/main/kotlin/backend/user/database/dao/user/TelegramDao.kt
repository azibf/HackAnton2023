package backend.user.database.dao.user

import backend.shared.database.AbstractDao
import backend.user.database.entity.user.Telegram

interface TelegramDao : AbstractDao<Telegram> {
    fun existsByTelegramId(id: String): Boolean
    fun findByChatId(id: String): Telegram?
}
