package buyticket.auth.servicies

import buyticket.auth.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {

    fun findById(id: Int): buyticket.auth.models.User? {
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
}