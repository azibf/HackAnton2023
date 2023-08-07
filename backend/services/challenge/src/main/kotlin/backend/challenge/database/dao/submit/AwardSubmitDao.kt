package backend.challenge.database.dao.submit

import backend.challenge.database.entity.submit.AwardSubmit
import java.util.UUID

interface AwardSubmitDao : SubmitDao<AwardSubmit> {
    fun existsByUserIdAndAwardId(userId: UUID, awardId: UUID): Boolean
    fun existsByTeamIdAndAwardId(teamId: UUID, awardId: UUID): Boolean
}
