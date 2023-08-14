package ir.smartech.cro.analytics.adapter.postgres

import ir.smartech.cro.analytics.adapter.postgres.entity.BaseEntity
import ir.smartech.cro.analytics.adapter.postgres.repository.JpaFunnelRepository
import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(basePackageClasses = [BaseEntity::class])
@EnableJpaRepositories(basePackageClasses = [JpaFunnelRepository::class])
@ComponentScan(basePackageClasses = [PostgresConfig::class])
@Configuration
class PostgresConfig{
    @Bean
    fun modelMapper(): ModelMapper = ModelMapper()
}