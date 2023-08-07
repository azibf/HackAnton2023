package backend.user.model.mapper

import backend.shared.model.ListResponse
import backend.shared.model.PageResponse
import backend.user.database.entity.team.Team
import backend.user.database.entity.user.User
import backend.user.model.dto.team.ParticipantResponse
import backend.user.model.dto.team.TeamRequest
import backend.user.model.dto.team.TeamResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class TeamMapper {
    fun asEntity(request: TeamRequest) = Team(
        name = request.name,
    )

    fun update(team: Team, request: TeamRequest): Team {
        team.name = request.name
        return team
    }

    fun asResponse(team: Team) = TeamResponse(
        id = team.id,
        name = team.name,
    )

    fun asResponse(participant: User) = ParticipantResponse(
        id = participant.id,
        name = participant.name,
    )

    fun asPageResponse(items: Page<Team>) = PageResponse.build(items, ::asResponse)

    fun asParticipantsResponse(items: List<User>) = ListResponse.build(items, ::asResponse)

    fun asListResponse(items: Iterable<Team>) = ListResponse.build(items, ::asResponse)
}
