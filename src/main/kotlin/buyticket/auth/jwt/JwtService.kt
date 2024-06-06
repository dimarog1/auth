package buyticket.auth.jwt

import buyticket.auth.models.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import java.util.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import javax.crypto.SecretKey

@Service
class JwtService(
    @Value("\${jwt.secret}")
    private val secretKey: String
) {
    fun extractAllClaims(token: String): Claims {
        return Jwts.parser().verifyWith(signingKey())
            .build().parseSignedClaims(token).payload
    }

    fun isValid(token: String, user: UserDetails): Boolean {
        return extractAllClaims(token).subject == user.username && !extractAllClaims(token).expiration.before(Date())
    }

//    fun createToken(user: User): String {
////        val jwsHeader = JwsHeader.with { "HmacSHA256" }.build()
////        val claims = JwtClaimsSet.builder()
////            .subject(user.nickname)
////            .issuedAt(Instant.now())
////            .expiresAt(Instant.now().plus(1L, ChronoUnit.HOURS))
////            .claim("userId", user.id)
////            .build()
//
//        val token = Jwts.builder()
//            .signWith(signingKey())
//            .setIssuedAt(Date(System.currentTimeMillis()))
//            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
//            .compact()
//
//        return token
////        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
//    }

    fun generateToken(user: User): String {
        return Jwts.builder().subject(user.email)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .signWith(signingKey())
            .compact()
    }

    private fun signingKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}