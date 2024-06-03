package buyticket.auth.repositories

import buyticket.auth.models.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Int> {

    fun findByUserId(userId: Int): Session?
}