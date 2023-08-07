package backend.challenge.database.dao.score

import backend.challenge.database.entity.score.TeamScore
import backend.shared.database.AbstractDao
import java.util.UUID

interface TeamScoreDao : AbstractDao<TeamScore> {
    fun findTeamScoreByTeamId(id: UUID): TeamScore?
}
