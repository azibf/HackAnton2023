package backend.challenge.database.dao.challenge

import backend.challenge.database.entity.challenge.Challenge
import backend.shared.database.AbstractDao
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface ChallengeDao : AbstractDao<Challenge> {
    fun existsByName(name: String): Boolean

    @Query(
        """
        SELECT c FROM Challenge c WHERE c.visible = true AND (
            (c.start IS NULL OR c.start <= :now) AND (c.end IS NULL OR c.end >= :now)
        )
        """,
    )
    fun findAvailable(pageable: Pageable, now: LocalDateTime = LocalDateTime.now()): Page<Challenge>
}
