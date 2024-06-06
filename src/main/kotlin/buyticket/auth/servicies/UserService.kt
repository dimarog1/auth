package buyticket.auth.servicies

import buyticket.auth.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    fun findById(id: Long): buyticket.auth.models.User? {
        return userRepository.findByIdOrNull(id)
    }

    fun findByNickname(nickname: String): buyticket.auth.models.User? {
        return userRepository.findByNickname(nickname)
    }

    fun findByEmail(email: String): buyticket.auth.models.User? {
        return userRepository.findByEmail(email)
    }

    fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun save(user: buyticket.auth.models.User): buyticket.auth.models.User {
        return userRepository.save(user)
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        if (email == null) {
            throw UsernameNotFoundException("There is no such user")
        }

        val user = userRepository.findByEmail(email) ?: throw UsernameNotFoundException("There is no such user")

        return User(user.email, user.password, emptyList())
    }
}