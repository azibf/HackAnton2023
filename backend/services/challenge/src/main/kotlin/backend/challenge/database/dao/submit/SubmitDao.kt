package backend.challenge.database.dao.submit

import backend.challenge.database.entity.submit.AbstractSubmit
import backend.shared.database.AbstractDao
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.NoRepositoryBean
import java.util.UUID

@NoRepositoryBean
interface SubmitDao<T : AbstractSubmit> : AbstractDao<T> {
    fun findByUserId(userId: UUID, pageable: Pageable): Page<T>
    fun findByTeamId(teamId: UUID, pageable: Pageable): Page<T>
    fun findByTeamIdNull(): List<T>
    fun findByTeamIdNotNull(): List<T>
}
