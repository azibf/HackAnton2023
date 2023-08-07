package backend.challenge.database.dao.challenge

import backend.challenge.database.entity.challenge.Award
import backend.shared.database.AbstractDao
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

interface AwardDao : AbstractDao<Award> {
    fun existsByName(name: String): Boolean

    @Query("SELECT c FROM Award c WHERE c.visible = true")
    fun findAvailable(pageable: Pageable): Page<Award>
}
