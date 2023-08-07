package backend.user.database.dao.user

import backend.shared.database.AbstractDao
import backend.user.database.entity.user.User

interface UserDao : AbstractDao<User> {
    fun existsByToken(token: String): Boolean
    fun findByToken(token: String): User?
}
