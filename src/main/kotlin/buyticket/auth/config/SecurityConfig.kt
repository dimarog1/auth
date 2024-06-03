package buyticket.auth.config

import buyticket.auth.servicies.TokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer
import org.springframework.security.web.*
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * This class sets all security related configuration.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenService: TokenService,
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { auth: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry ->
            auth.requestMatchers("/**", "/api/register", "/api/login").permitAll()
                .anyRequest().authenticated()
        }.csrf { csrf -> csrf.disable() }

        return http.build()
    }

//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        // allow localhost for dev purposes
//        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8080")
//        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
//        configuration.allowedHeaders = listOf("authorization", "content-type")
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }
}