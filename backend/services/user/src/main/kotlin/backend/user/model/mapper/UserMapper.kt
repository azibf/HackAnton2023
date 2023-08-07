package backend.user.model.mapper

import backend.shared.model.ListResponse
import backend.shared.model.PageResponse
import backend.user.database.entity.user.Telegram
import backend.user.database.entity.user.User
import backend.user.model.dto.user.LinkTelegramRequest
import backend.user.model.dto.user.UserRequest
import backend.user.model.dto.user.UserResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class UserMapper(
    private val teamMapper: TeamMapper,
) {
    fun asEntity(request: UserRequest) = User(
        name = request.name,
        token = request.token,
    )

    fun asEntity(user: User, request: LinkTelegramRequest) = Telegram(
        user = user,
        telegramId = request.telegramId,
        chatId = request.chatId,
    )

    fun update(user: User, request: UserRequest): User {
        user.name = request.name
        user.token = request.token
        return user
    }

    fun asResponse(user: User) = UserResponse(
        id = user.id,
        name = user.name,
        admin = user.admin,
        team = user.team?.let { teamMapper.asResponse(it) },
    )

    fun asPageResponse(items: Page<User>) = PageResponse.build(items, ::asResponse)

    fun asListResponse(items: Iterable<User>) = ListResponse.build(items, ::asResponse)
}
