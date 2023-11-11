package ir.smartech.cro.storage.service

import ir.smartech.cro.storage.data.postgres.dto.ClientSchemaDto
import ir.smartech.cro.storage.data.postgres.entity.ClientSchema
import ir.smartech.cro.storage.data.postgres.entity.Client
import ir.smartech.cro.storage.data.postgres.repository.ClientSchemaRepository
import ir.smartech.cro.storage.data.postgres.repository.ClientRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

/**
 * Service class for [Client] entity providing CRUD functionalities and [ClientSchema] modifications
 */
@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val clientSchemaRepository: ClientSchemaRepository,
    private val passwordEncoder: PasswordEncoder
) {
    /**
     * create and update client
     */
    fun upsert(entity: Client): Client {
        entity.password = passwordEncoder.encode(entity.password)
        return clientRepository.save(entity)
    }

    /**
     * return client by id
     */
    fun getById(id: Int): Optional<Client> {
        return clientRepository.findById(id)
    }

    /**
     * get all client
     */
    fun getAll(): List<Client> {
        return clientRepository.findAll()
    }

    /**
     * create and update currentClient's schema
     */
    fun setSchema(dto: ClientSchemaDto) {
        // TODO get client from context
        val client = getAll().first()
        val toBeSave = mapToEntity(dto, client)
        clientSchemaRepository.save(toBeSave)
    }

    /**
     * get schema by id
     */
    fun getSchema(id: Int): ClientSchema = clientSchemaRepository.findById(id).orElseThrow()

    private fun mapToEntity(dto: ClientSchemaDto, client: Client) =
        ClientSchema().apply {
            id = dto.id
            this.client = client
            data = dto.data
        }
}