package ir.smartech.cro.storage.config

import org.springframework.security.test.context.support.WithSecurityContext

@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = WithMockUserDtoSecurityContextFactory::class)
annotation class WithMockUserDto(
    val id: Int = 1,
    val username: String = "test@smartech.ir",
    val password: String = "111111",
)