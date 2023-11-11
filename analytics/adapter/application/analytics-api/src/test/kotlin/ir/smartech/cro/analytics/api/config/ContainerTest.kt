package ir.smartech.cro.analytics.api.config

import org.springframework.test.context.ContextConfiguration

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ContextConfiguration(initializers = [TestContainerInitializer::class])
annotation class ContainerTest()
