package buyticket.auth.repositories

import buyticket.auth.models.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Long> {

    fun findByUserId(userId: Int): Session?

    fun findByToken(token: String) : Session?
}