package backend.challenge.service.impl

import backend.challenge.database.dao.challenge.AwardDao
import backend.challenge.model.dto.challenge.ChallengeRequest
import backend.challenge.model.dto.challenge.ChallengeResponse
import backend.challenge.model.dto.challenge.SubmitRequest
import backend.challenge.model.exception.ChallengeNameAlreadyExistsException
import backend.challenge.model.mapper.AwardMapper
import backend.challenge.service.AwardService
import backend.challenge.service.SubmitService
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import backend.shared.model.exception.ResourceNotFoundException
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class AwardServiceImpl(
    private val awardDao: AwardDao,
    private val mapper: AwardMapper,
    private val submitService: SubmitService,
) : AwardService {

    override fun list(page: PageRequest, userId: UUID): PageResponse<ChallengeResponse> {
        val awards = awardDao.findAvailable(page.pageable)
        return mapper.asPageResponse(awards, userId)
    }

    override fun create(request: ChallengeRequest.Award): ChallengeResponse {
        if (awardDao.existsByName(request.name)) {
            throw ChallengeNameAlreadyExistsException()
        }
        val entity = mapper.asEntity(request)
        val award = awardDao.save(entity)
        return mapper.asResponse(award)
    }

    override fun findById(id: UUID, userId: UUID): ChallengeResponse {
        val award = awardDao.findEntityById(id) ?: throw ResourceNotFoundException()
        return mapper.asResponse(award, userId)
    }

    @Modifying
    @Transactional
    override fun update(id: UUID, request: ChallengeRequest.Award): ChallengeResponse {
        val award = awardDao.findEntityById(id) ?: throw ResourceNotFoundException()
        val updated = mapper.updated(award, request)
        return mapper.asResponse(updated)
    }

    @Modifying
    @Transactional
    override fun delete(id: UUID) {
        val award = awardDao.findEntityById(id) ?: throw ResourceNotFoundException()
        awardDao.delete(award)
    }

    override fun submit(id: UUID, request: SubmitRequest.Award) = submitService.submit(id, request)
}
