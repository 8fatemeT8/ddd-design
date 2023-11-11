package ir.smartech.cro.analytics.rdb

import ir.smartech.cro.analytics.rdb.entity.BaseEntity
import ir.smartech.cro.analytics.rdb.repository.JpaFunnelRepository
import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(basePackageClasses = [BaseEntity::class])
@EnableJpaRepositories(basePackageClasses = [JpaFunnelRepository::class])
@ComponentScan(basePackageClasses = [RdbConfig::class])
@Configuration
class RdbConfig{
    @Bean
    fun modelMapper(): ModelMapper = ModelMapper()
}