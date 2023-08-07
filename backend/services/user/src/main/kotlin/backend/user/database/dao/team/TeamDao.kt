package backend.user.database.dao.team

import backend.shared.database.AbstractDao
import backend.user.database.entity.team.Team

interface TeamDao : AbstractDao<Team> {
    fun existsByName(name: String): Boolean
}
