package backend.user.database.entity.user

import backend.shared.database.AbstractEntity
import backend.user.util.UserConstant
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(indexes = [Index(columnList = "telegramId")])
class Telegram(

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    val user: User,

    @Column(nullable = false, length = UserConstant.TELEGRAM_ID_LENGTH)
    val telegramId: String,

    @Column(nullable = false, unique = true, length = UserConstant.TELEGRAM_ID_LENGTH)
    val chatId: String,

    ) : AbstractEntity()
