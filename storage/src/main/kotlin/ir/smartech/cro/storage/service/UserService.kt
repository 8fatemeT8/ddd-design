package ir.smartech.cro.storage.service

import ir.smartech.cro.storage.data.postgres.dto.ProjectSchemaDto
import ir.smartech.cro.storage.data.postgres.entity.ProjectSchema
import ir.smartech.cro.storage.data.postgres.entity.User
import ir.smartech.cro.storage.data.postgres.repository.ProjectSchemaRepository
import ir.smartech.cro.storage.data.postgres.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

/**
 * this class uses for User logic
 */
@Service
class UserService(
    private val userRepository: UserRepository,
    private val projectSchemaRepository: ProjectSchemaRepository,
) {
    /**
     * create and update user
     */
    fun upsert(entity: User): User {
        return userRepository.save(entity)
    }

    /**
     * return user by id
     */
    fun getById(id: Int): Optional<User> {
        return userRepository.findById(id)
    }

    /**
     * get all user
     */
    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    /**
     * create and update currentUser's schema
     */
    fun setSchema(dto: ProjectSchemaDto) {
        // TODO get user from context
        val user = getAll().first()
        val toBeSave = mapToEntity(dto, user)
        projectSchemaRepository.save(toBeSave)
    }

    /**
     * get schema by id
     */
    fun getSchema(id: Int): ProjectSchema = projectSchemaRepository.findById(id).orElseThrow()

    private fun mapToEntity(dto: ProjectSchemaDto, user: User) =
        ProjectSchema().apply {
            id = dto.id
            this.user = user
            data = dto.data
        }
}