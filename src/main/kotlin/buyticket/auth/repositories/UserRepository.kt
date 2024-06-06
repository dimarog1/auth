package buyticket.auth.repositories

import buyticket.auth.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByNickname(nickname: String): User?

    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean
}