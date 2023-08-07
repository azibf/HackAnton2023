package backend.user.service.impl

import backend.shared.model.ListResponse
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import backend.shared.model.exception.ResourceNotFoundException
import backend.user.database.dao.user.TelegramDao
import backend.user.database.dao.user.UserDao
import backend.user.model.dto.user.LinkTelegramRequest
import backend.user.model.dto.user.UnlinkTelegramRequest
import backend.user.model.dto.user.UserRequest
import backend.user.model.dto.user.UserResponse
import backend.user.model.exception.TelegramIdAlreadyExistsException
import backend.user.model.exception.TokenAlreadyExistsException
import backend.user.model.mapper.UserMapper
import backend.user.service.UserService
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UserServiceImpl(
    private val userDao: UserDao,
    private val telegramDao: TelegramDao,
    private val mapper: UserMapper,
) : UserService {

    override fun list(page: PageRequest): PageResponse<UserResponse> {
        val users = userDao.findAll(page.pageable)
        return mapper.asPageResponse(users)
    }

    override fun create(request: UserRequest): UserResponse {
        if (userDao.existsByToken(request.token)) {
            throw TokenAlreadyExistsException()
        }
        val entity = mapper.asEntity(request)
        val user = userDao.save(entity)
        return mapper.asResponse(user)
    }

    override fun findById(id: UUID): UserResponse {
        val user = userDao.findEntityById(id) ?: throw ResourceNotFoundException()
        return mapper.asResponse(user)
    }

    override fun listById(ids: List<UUID>): ListResponse<UserResponse> {
        val users = userDao.findAllById(ids)
        return mapper.asListResponse(users)
    }

    @Modifying
    @Transactional
    override fun update(id: UUID, request: UserRequest): UserResponse {
        val user = userDao.findEntityById(id) ?: throw ResourceNotFoundException()
        val updated = mapper.update(user, request)
        return mapper.asResponse(updated)
    }

    @Modifying
    @Transactional
    override fun delete(id: UUID) {
        val user = userDao.findEntityById(id) ?: throw ResourceNotFoundException()
        userDao.delete(user)
    }

    override fun linkTelegram(request: LinkTelegramRequest) {
        if (telegramDao.existsByTelegramId(request.telegramId)) {
            throw TelegramIdAlreadyExistsException()
        }
        val user = userDao.findByToken(request.token) ?: throw ResourceNotFoundException()
        val entity = mapper.asEntity(user, request)
        telegramDao.save(entity)
    }

    @Modifying
    @Transactional
    override fun unlinkTelegram(request: UnlinkTelegramRequest) {
        val telegram = telegramDao.findByChatId(request.chatId) ?: throw ResourceNotFoundException()
        telegramDao.delete(telegram)
    }

    override fun findByChatId(id: String): UserResponse {
        val telegram = telegramDao.findByChatId(id) ?: throw ResourceNotFoundException()
        return mapper.asResponse(telegram.user)
    }

    override fun findByToken(token: String): UserResponse {
        val user = userDao.findByToken(token) ?: throw ResourceNotFoundException()
        return mapper.asResponse(user)
    }
}
