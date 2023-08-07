package backend.challenge.model.dto.score

import backend.challenge.model.dto.user.TeamDto
import backend.challenge.model.dto.user.UserDto
import backend.shared.model.PageResponse

sealed class ScoreResponse(
    val score: Double,
) {
    class History(
        score: Double,

        val items: PageResponse<HistoryItemResponse>,
    ) : ScoreResponse(
        score = score,
    )

    class Team(
        score: Double,

        val team: TeamDto,
    ) : ScoreResponse(
        score = score,
    )

    class User(
        score: Double,

        val user: UserDto,
    ) : ScoreResponse(
        score = score,
    )
}
