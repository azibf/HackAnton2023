package backend.challenge.database.dao.submit

import backend.challenge.database.entity.submit.ChallengeSubmit
import java.util.UUID

interface ChallengeSubmitDao : SubmitDao<ChallengeSubmit> {
    fun existsByUserIdAndChallengeId(userId: UUID, challengeId: UUID): Boolean
    fun existsByTeamIdAndChallengeId(teamId: UUID, challengeId: UUID): Boolean
}
