package backend.user.service

import backend.shared.model.ListResponse
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import backend.user.model.dto.user.LinkTelegramRequest
import backend.user.model.dto.user.UnlinkTelegramRequest
import backend.user.model.dto.user.UserRequest
import backend.user.model.dto.user.UserResponse
import java.util.UUID

interface UserService {
    fun list(page: PageRequest): PageResponse<UserResponse>
    fun listById(ids: List<UUID>): ListResponse<UserResponse>
    fun create(request: UserRequest): UserResponse
    fun findById(id: UUID): UserResponse
    fun update(id: UUID, request: UserRequest): UserResponse
    fun delete(id: UUID)
    fun linkTelegram(request: LinkTelegramRequest)
    fun unlinkTelegram(request: UnlinkTelegramRequest)
    fun findByChatId(id: String): UserResponse
    fun findByToken(token: String): UserResponse
}
