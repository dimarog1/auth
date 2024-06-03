package buyticket.auth.dto

data class LoginDto(
    val email: String,
    val password: String,
)

data class RegisterDto(
    val nickName: String,
    val password: String,
    val email: String,
)