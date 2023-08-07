package backend.challenge.database.dao.score

import backend.challenge.database.entity.score.UserScore
import backend.shared.database.AbstractDao
import java.util.UUID

interface UserScoreDao : AbstractDao<UserScore> {
    fun findUserScoreByUserId(id: UUID): UserScore?
}
