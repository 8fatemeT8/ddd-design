package ir.smartech.cro.analytics.adapter.compositionroot

import ir.smartech.cro.analytics.adapter.postgres.PostgresConfig
import ir.smartech.cro.analytics.domain.funnel.api.FunnelService
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(PostgresConfig::class)
class CompositionConfig {
    @Bean
    fun funnelService(funnelRepository: FunnelRepository): FunnelService = FunnelService(funnelRepository)
}

