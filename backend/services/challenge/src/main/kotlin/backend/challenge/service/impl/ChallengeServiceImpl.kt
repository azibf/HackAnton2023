package backend.challenge.service.impl

import backend.challenge.database.dao.challenge.ChallengeDao
import backend.challenge.model.dto.challenge.ChallengeRequest
import backend.challenge.model.dto.challenge.ChallengeResponse
import backend.challenge.model.dto.challenge.SubmitRequest
import backend.challenge.model.exception.ChallengeNameAlreadyExistsException
import backend.challenge.model.mapper.ChallengeMapper
import backend.challenge.service.ChallengeService
import backend.challenge.service.SubmitService
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import backend.shared.model.exception.ResourceNotFoundException
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ChallengeServiceImpl(
    private val challengeDao: ChallengeDao,
    private val mapper: ChallengeMapper,
    private val submitService: SubmitService,
) : ChallengeService {

    override fun list(page: PageRequest, userId: UUID): PageResponse<ChallengeResponse> {
        val challenges = challengeDao.findAvailable(page.pageable)
        return mapper.asPageResponse(challenges, userId)
    }

    override fun create(request: ChallengeRequest.Challenge): ChallengeResponse {
        if (challengeDao.existsByName(request.name)) {
            throw ChallengeNameAlreadyExistsException()
        }
        val entity = mapper.asEntity(request)
        val challenge = challengeDao.save(entity)
        return mapper.asResponse(challenge)
    }

    override fun findById(id: UUID, userId: UUID): ChallengeResponse {
        val challenge = challengeDao.findEntityById(id) ?: throw ResourceNotFoundException()
        return mapper.asResponse(challenge, userId)
    }

    @Modifying
    @Transactional
    override fun update(id: UUID, request: ChallengeRequest.Challenge): ChallengeResponse {
        val challenge = challengeDao.findEntityById(id) ?: throw ResourceNotFoundException()
        val updated = mapper.updated(challenge, request)
        return mapper.asResponse(updated)
    }

    @Modifying
    @Transactional
    override fun delete(id: UUID) {
        val challenge = challengeDao.findEntityById(id) ?: throw ResourceNotFoundException()
        challengeDao.delete(challenge)
    }

    override fun submit(id: UUID, request: SubmitRequest.Challenge) = submitService.submit(id, request)
}
