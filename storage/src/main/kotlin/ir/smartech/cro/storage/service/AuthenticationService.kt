package ir.smartech.cro.storage.service

import ir.smartech.cro.storage.config.security.JwtResponseDto
import ir.smartech.cro.storage.config.security.JwtTokenUtil
import ir.smartech.cro.storage.config.security.JwtUserDetailsService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.lang.RuntimeException


@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: JwtUserDetailsService,
    private val jwtTokenUtil: JwtTokenUtil
) {

    fun authenticate(username: String?, password: String?): JwtResponseDto? {
        return try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            val token: String = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(username!!))
            JwtResponseDto(token)
        } catch (e: DisabledException) {
            throw RuntimeException()
        } catch (e: BadCredentialsException) {
            throw RuntimeException()
        }
    }
}