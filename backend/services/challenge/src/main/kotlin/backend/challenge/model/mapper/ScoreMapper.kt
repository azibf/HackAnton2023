package backend.challenge.model.mapper

import backend.challenge.database.entity.score.TeamScore
import backend.challenge.database.entity.score.UserScore
import backend.challenge.database.entity.submit.AbstractSubmit
import backend.challenge.database.entity.submit.AwardSubmit
import backend.challenge.database.entity.submit.ChallengeSubmit
import backend.challenge.model.dto.score.HistoryItemResponse
import backend.challenge.model.dto.score.ProfileScoreResponse
import backend.challenge.model.dto.score.ScoreResponse
import backend.challenge.model.dto.user.ProfileDto
import backend.challenge.model.dto.user.TeamDto
import backend.challenge.model.dto.user.UserDto
import backend.shared.model.PageResponse
import backend.shared.model.exception.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ScoreMapper(
    private val awardMapper: AwardMapper,
    private val challengeMapper: ChallengeMapper
) {

    fun asUserScoreEntity(userId: UUID) = UserScore(
        userId = userId,
    )

    fun asTeamScoreEntity(teamId: UUID) = TeamScore(
        teamId = teamId,
    )

    fun asResponse(submit: AbstractSubmit) = HistoryItemResponse(
        id = submit.id,
        submitted = submit.createdAt,
        challenge = when (submit) {
            is ChallengeSubmit -> challengeMapper.asResponse(submit.challenge)
            is AwardSubmit -> awardMapper.asResponse(submit.award)
        }
    )

    fun asUserScoreboard(scores: Page<UserScore>, users: List<ProfileDto>) = PageResponse.build(scores) { score ->
        val user = users.find { user -> score.userId == user.id } ?: throw ResourceNotFoundException()
        asResponse(user, score.score)
    }

    fun asTeamScoreboard(scores: Page<TeamScore>, teams: List<TeamDto>) = PageResponse.build(scores) { score ->
        val team = teams.find { team -> score.teamId == team.id } ?: throw ResourceNotFoundException()
        asResponse(team, score.score)
    }

    fun asHistoryResponse(response: ScoreResponse, items: Page<out AbstractSubmit>) = ScoreResponse.History(
        score = response.score,
        items = PageResponse.build(items, ::asResponse),
    )

    fun asResponse(user: UserDto, score: Double) = ScoreResponse.User(
        score = score,
        user = user,
    )

    fun asResponse(team: TeamDto, score: Double) = ScoreResponse.Team(
        score = score,
        team = team,
    )

    fun asResponse(user: ProfileDto, userScore: Double, teamScore: Double) = ProfileScoreResponse(
        user = asResponse(user, userScore),
        team = user.team?.let { asResponse(user.team, teamScore) },
    )
}
