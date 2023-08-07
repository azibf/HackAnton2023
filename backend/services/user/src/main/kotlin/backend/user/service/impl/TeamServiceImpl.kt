package backend.user.service.impl

import backend.shared.model.ListResponse
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import backend.shared.model.exception.ResourceNotFoundException
import backend.user.database.dao.team.TeamDao
import backend.user.database.dao.user.UserDao
import backend.user.model.dto.team.ParticipantResponse
import backend.user.model.dto.team.ParticipateRequest
import backend.user.model.dto.team.TeamRequest
import backend.user.model.dto.team.TeamResponse
import backend.user.model.exception.InvalidInviteException
import backend.user.model.exception.TeamAlreadyParticipatedException
import backend.user.model.exception.TeamNameAlreadyExistsException
import backend.user.model.mapper.TeamMapper
import backend.user.service.TeamService
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class TeamServiceImpl(
    private val userDao: UserDao,
    private val teamDao: TeamDao,
    private val mapper: TeamMapper,
) : TeamService {

    override fun list(page: PageRequest): PageResponse<TeamResponse> {
        val teams = teamDao.findAll(page.pageable)
        return mapper.asPageResponse(teams)
    }

    override fun listById(ids: List<UUID>): ListResponse<TeamResponse> {
        val teams = teamDao.findAllById(ids)
        return mapper.asListResponse(teams)
    }

    override fun create(request: TeamRequest): TeamResponse {
        if (teamDao.existsByName(request.name)) {
            throw TeamNameAlreadyExistsException()
        }
        val entity = mapper.asEntity(request)
        val team = teamDao.save(entity)
        return mapper.asResponse(team)
    }

    override fun findById(id: UUID): TeamResponse {
        val team = teamDao.findEntityById(id) ?: throw ResourceNotFoundException()
        return mapper.asResponse(team)
    }

    @Modifying
    @Transactional
    override fun update(id: UUID, request: TeamRequest): TeamResponse {
        val team = teamDao.findEntityById(id) ?: throw ResourceNotFoundException()
        val updated = mapper.update(team, request)
        return mapper.asResponse(updated)
    }

    override fun delete(id: UUID) {
        val team = teamDao.findEntityById(id) ?: throw ResourceNotFoundException()
        teamDao.delete(team)
    }

    override fun listParticipants(id: UUID, page: PageRequest): ListResponse<ParticipantResponse> {
        val team = teamDao.findEntityById(id) ?: throw ResourceNotFoundException()
        return mapper.asParticipantsResponse(team.participants)
    }

    @Modifying
    @Transactional
    override fun participate(id: UUID, request: ParticipateRequest) {
        val team = teamDao.findEntityById(id) ?: throw ResourceNotFoundException()
        val user = userDao.findEntityById(request.userId) ?: throw ResourceNotFoundException()
        if (user in team.participants) throw TeamAlreadyParticipatedException()
        if (team.invite != request.invite) throw InvalidInviteException()
        user.team = team
    }
}
