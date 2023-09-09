package ir.smartech.cro.storage.config

import ir.smartech.cro.storage.data.postgres.entity.Client
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory


class WithMockUserDtoSecurityContextFactory : WithSecurityContextFactory<WithMockUserDto> {
    override fun createSecurityContext(withMockUserDto: WithMockUserDto?): SecurityContext {
        val context = SecurityContextHolder.createEmptyContext()

        val principal = Client().apply {
            id =withMockUserDto?.id
            username = withMockUserDto?.username
            password = withMockUserDto?.password
        }
        val auth: Authentication =
            UsernamePasswordAuthenticationToken(principal, principal.password, principal.authorities)
        context.authentication = auth
        return context
    }
}