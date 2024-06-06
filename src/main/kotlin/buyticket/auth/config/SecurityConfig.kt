package buyticket.auth.config

import buyticket.auth.jwt.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.*
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * This class sets all security related configuration.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(val jwtFilter: JwtFilter) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests { auth ->
//                auth.requestMatchers("/**").permitAll()
                auth
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/index.html").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/register").permitAll()
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
//            .formLogin { login ->
//                login
//                    .defaultSuccessUrl("/auth/register")
//                    .permitAll()
//            }


        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.authenticationManager
    }
}