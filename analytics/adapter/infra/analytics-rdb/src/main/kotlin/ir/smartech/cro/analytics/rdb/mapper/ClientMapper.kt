package ir.smartech.cro.analytics.rdb.mapper

import ir.smartech.cro.analytics.rdb.entity.JpaClient
import ir.smartech.cro.analytics.domain.client.api.entity.Client
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

/**
 * this class maps client from domain to entity and vice versa
 * toDestination : from Client to JpaClient
 * toSource : from JpaClient to Client
 */
@Component
class ClientMapper(private val modelMapper: ModelMapper) : Mapper<Client, JpaClient> {
    override fun toDestination(dto: Client): JpaClient     = modelMapper.map(dto, JpaClient::class.java)
    override fun toSource(dto: JpaClient): Client = modelMapper.map(dto, Client::class.java)
}