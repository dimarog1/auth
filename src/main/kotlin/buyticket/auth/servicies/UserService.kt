package buyticket.auth.servicies

import buyticket.auth.dto.ApiException
import buyticket.auth.dto.UserResponseDto
import buyticket.auth.repositories.UserRepository
import buyticket.auth.servicies.SessionService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository,
    private val sessionService: SessionService,
) : UserDetailsService {

    fun getInfo(auth: String): UserResponseDto {
        if (auth.isBlank()) {
            throw ApiException(code = 400, message = "Token required")
        }

        val token = auth.substring(7)
        val session = sessionService.findByToken(token) ?: throw ApiException(
            code = 400,
            message = "Token not found."
        )

        val user = session.user?.let { findByNickname(it.nickname) } ?: throw ApiException(
            code = 400,
            message = "User not found"
        )

        return UserResponseDto(
            code = 200,
            message = "Successfully.",
            nickName = user.nickname,
            email = user.email,
            id = user.id,
        )
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