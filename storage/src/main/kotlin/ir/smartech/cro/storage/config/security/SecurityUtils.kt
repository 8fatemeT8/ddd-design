package ir.smartech.cro.storage.config.security

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import java.util.*


class SecurityUtils private constructor() {

    companion object {
        fun getCurrentClientLogin(): Optional<String?>? {
            val securityContext: SecurityContext = SecurityContextHolder.getContext()
            return Optional.ofNullable(securityContext.authentication)
                .map { authentication ->
                    if (authentication.principal is UserDetails) {
                        val springSecurityUser = authentication.principal as UserDetails
                        return@map springSecurityUser.username
                    } else if (authentication.principal is String) {
                        return@map authentication.principal as String
                    }
                    null
                }
        }
    }
}