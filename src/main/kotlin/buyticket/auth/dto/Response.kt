package buyticket.auth.dto

import org.springframework.web.server.ResponseStatusException

/**
 * This file contains all outgoing DTOs.
 * [ApiException] is used to easily throw exceptions.
 */
class ApiException(val code: Int, message: String) : ResponseStatusException(code, message, null)

data class LoginResponseDto(
    val code: Int,
    val message: String,
    val token: String,
)

data class RegisterResponseDto(
    val code: Int,
    val message: String,
)