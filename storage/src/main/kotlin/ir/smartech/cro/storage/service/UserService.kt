package ir.smartech.cro.storage.service

import ir.smartech.cro.storage.config.security.JwtUserDetailsService
import ir.smartech.cro.storage.data.postgres.dto.ProjectSchemaDto
import ir.smartech.cro.storage.data.postgres.entity.User
import ir.smartech.cro.storage.data.postgres.repository.UserRepository
import ir.smartech.cro.storage.data.postgres.repository.ProjectSchemaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional
import ir.smartech.cro.storage.data.postgres.entity.ProjectSchema

@Service
class UserService(
    private val userRepository: UserRepository,
    private val projectSchemaRepository: ProjectSchemaRepository,
    private val jwtUserDetailsService: JwtUserDetailsService,
    private val passwordEncoder: PasswordEncoder
) {
    fun upsert(entity: User): User {
        entity.password = passwordEncoder.encode(entity.password)
        return userRepository.save(entity)
    }

    fun getById(id: Int): Optional<User> {
        return userRepository.findById(id)
    }

    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    fun setSchema(dto: ProjectSchemaDto) {
        val user = jwtUserDetailsService.getCurrentUser() ?: return
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