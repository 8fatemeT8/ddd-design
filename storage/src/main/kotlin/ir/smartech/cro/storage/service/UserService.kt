package ir.smartech.cro.storage.service

import ir.smartech.cro.storage.data.postgres.dto.ProjectSchemaDto
import ir.smartech.cro.storage.data.postgres.entity.ProjectSchema
import ir.smartech.cro.storage.data.postgres.entity.User
import ir.smartech.cro.storage.data.postgres.repository.ProjectSchemaRepository
import ir.smartech.cro.storage.data.postgres.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val projectSchemaRepository: ProjectSchemaRepository,
) {
    fun upsert(entity: User): User {
        return userRepository.save(entity)
    }

    fun getById(id: Int): Optional<User> {
        return userRepository.findById(id)
    }

    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    fun setSchema(dto: ProjectSchemaDto) {
        // TODO get user from context
        val user = getAll().first()
        val toBeSave = mapToEntity(dto, user)
        projectSchemaRepository.save(toBeSave)
    }

    fun getSchema(id: Int): ProjectSchema = projectSchemaRepository.findById(id).orElseThrow()

    private fun mapToEntity(dto: ProjectSchemaDto, user: User) =
        ProjectSchema().apply {
            id = dto.id
            this.user = user
            data = dto.data
        }
}