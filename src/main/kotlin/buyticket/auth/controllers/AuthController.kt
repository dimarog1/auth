package buyticket.auth.controllers

import buyticket.auth.dto.*
import buyticket.auth.models.Session
import buyticket.auth.models.User
import buyticket.auth.servicies.HashService
import buyticket.auth.servicies.SessionService
import buyticket.auth.jwt.JwtService
import buyticket.auth.servicies.AuthService
import buyticket.auth.servicies.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.regex.Pattern

/**
 * This controller handles login and register requests.
 * Both routes are public as specified in SecurityConfig.
 */
@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): ResponseEntity<LoginResponseDto> {
        try {
            val response = authenticationService.login(payload)
            return ResponseEntity.ok().body(response)
        } catch (e: ApiException) {
            return ResponseEntity.badRequest().body(LoginResponseDto(code = e.code, message = e.message, token = ""))
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): ResponseEntity<RegisterResponseDto> {
        try {
            val response = authenticationService.register(payload)
            return ResponseEntity.ok().body(response)
        } catch (e: ApiException) {
            return ResponseEntity.badRequest().body(RegisterResponseDto(code = e.code, message = e.message))
        }
    }
}