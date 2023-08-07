package backend.challenge.model.dto.score

import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.util.UUID

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
sealed class ScoreRequest {
    class User(
        val userId: UUID,
    ) : ScoreRequest()

    class Team(
        val teamId: UUID,
    ) : ScoreRequest()
}
