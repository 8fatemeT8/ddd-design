package ir.smartech.cro.analytics.adapter.compositionroot

import ir.smartech.cro.analytics.rdb.PostgresConfig
import ir.smartech.cro.analytics.domain.funnel.api.FunnelService
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository
import ir.smartech.cro.analytics.domain.client.api.ClientService
import ir.smartech.cro.analytics.domain.client.spi.ClientRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * in this class we provide the service beans
 * (these services are located in domain module. we create the CompositionConfig as a bridge for using these classes in api module )
 */
@Configuration
@Import(PostgresConfig::class)
class CompositionConfig {
    @Bean
    fun funnelService(funnelRepository: FunnelRepository): FunnelService = FunnelService(funnelRepository)

    @Bean
    fun clientService(clientRepository: ClientRepository): ClientService = ClientService(clientRepository)
}

