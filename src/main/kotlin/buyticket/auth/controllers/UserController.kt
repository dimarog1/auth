package buyticket.auth.controllers

import buyticket.auth.dto.ApiException
import buyticket.auth.dto.UserResponseDto
import buyticket.auth.servicies.SessionService
import buyticket.auth.servicies.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/getInfo")
    fun login(
        @RequestHeader("Authorization") auth: String,
    ): ResponseEntity<UserResponseDto> {
        try {
            val response = userService.getInfo(auth)
            return ResponseEntity.ok().body(response)
        } catch (e: ApiException) {
            return ResponseEntity.badRequest()
                .body(UserResponseDto(code = e.code, message = e.message, nickName = "", email = "", id = -1))
        }
    }
}