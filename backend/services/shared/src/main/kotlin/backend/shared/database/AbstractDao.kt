package backend.shared.database

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface AbstractDao<T : AbstractEntity> : CrudRepository<T, UUID> {
    fun findAll(pageable: Pageable): Page<T>
    fun findEntityById(id: UUID): T?
}
