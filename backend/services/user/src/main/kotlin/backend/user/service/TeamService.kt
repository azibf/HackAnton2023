package backend.user.service

import backend.shared.model.ListResponse
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import backend.user.model.dto.team.ParticipantResponse
import backend.user.model.dto.team.ParticipateRequest
import backend.user.model.dto.team.TeamRequest
import backend.user.model.dto.team.TeamResponse
import java.util.UUID

interface TeamService {
    fun list(page: PageRequest): PageResponse<TeamResponse>
    fun listById(ids: List<UUID>): ListResponse<TeamResponse>
    fun create(request: TeamRequest): TeamResponse
    fun findById(id: UUID): TeamResponse
    fun update(id: UUID, request: TeamRequest): TeamResponse
    fun delete(id: UUID)
    fun listParticipants(id: UUID, page: PageRequest): ListResponse<ParticipantResponse>
    fun participate(id: UUID, request: ParticipateRequest)
}
