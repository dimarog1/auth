package buyticket.auth.controllers

import buyticket.auth.dto.*
import buyticket.auth.models.Session
import buyticket.auth.models.User
import buyticket.auth.servicies.HashService
import buyticket.auth.servicies.SessionService
import buyticket.auth.servicies.TokenService
import buyticket.auth.servicies.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * This controller handles login and register requests.
 * Both routes are public as specified in SecurityConfig.
 */
@RestController
@RequestMapping("/api")
class AuthController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
    private val sessionService: SessionService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByEmail(payload.email) ?: throw ApiException(400, "Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ApiException(400, "Login failed")
        }

        val session = Session(
            userId = user.id,
            token = tokenService.createToken(user),
        )

        sessionService.save(session)

        return LoginResponseDto(
            message = "Logged in successfully. Welcome, ${user.nickname}.",
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): RegisterResponseDto {
        if (userService.existsByEmail(payload.email)) {
            throw ApiException(400, "Email already exists")
        }

        val user = User(
            nickname = payload.nickName,
            password = hashService.hashBcrypt(payload.password),
            email = payload.email,
        )

        val savedUser = userService.save(user)

        return RegisterResponseDto(
            message = "Registered successfully. Now login, ${savedUser.nickname}.",
        )
    }
}