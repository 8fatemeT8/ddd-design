package ir.smartech.cro.analytics.adapter.compositionroot

import ir.smartech.cro.analytics.domain.event.api.EventService
import ir.smartech.cro.analytics.domain.event.spi.EventRepository
import ir.smartech.cro.analytics.rdb.PostgresConfig
import ir.smartech.cro.analytics.domain.funnel.api.FunnelService
import ir.smartech.cro.analytics.domain.funnel.spi.FunnelRepository
import ir.smartech.cro.analytics.domain.project.api.ProjectService
import ir.smartech.cro.analytics.domain.project.spi.ProjectRepository
import ir.smartech.cro.analytics.domain.property.api.PropertyService
import ir.smartech.cro.analytics.domain.property.spi.PropertyRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(PostgresConfig::class)
class CompositionConfig {
    @Bean
    fun funnelService(funnelRepository: FunnelRepository): FunnelService = FunnelService(funnelRepository)

    @Bean
    fun eventService(eventRepository: EventRepository): EventService = EventService(eventRepository)

    @Bean
    fun projectService(projectRepository: ProjectRepository): ProjectService = ProjectService(projectRepository)

    @Bean
    fun propertyService(propertyRepository: PropertyRepository): PropertyService = PropertyService(propertyRepository)
}

