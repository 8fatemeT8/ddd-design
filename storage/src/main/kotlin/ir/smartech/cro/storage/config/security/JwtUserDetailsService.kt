package ir.smartech.cro.storage.config.security

import ir.smartech.cro.storage.data.postgres.entity.User
import ir.smartech.cro.storage.data.postgres.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class JwtUserDetailsService : UserDetailsService {

    @Autowired
    private val bcryptEncoder: PasswordEncoder? = null

    @Autowired
    private val userRepository: UserRepository? = null

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository?.findByUsername(username)
        if (user == null)
            onUserNotFound()
        return user!!
    }

    fun getCurrentUser(): User? {
        val currentUser: Optional<String?>? = SecurityUtils.getCurrentUserLogin()
        if (currentUser?.isEmpty == true) {
            onUserNotFound()
        }
        val currentUserName: String = currentUser?.get()!!
        val user: User? = userRepository!!.findByUsername(currentUserName)
        if (user == null) {
            onUserNotFound()
        }
        return user
    }

    private fun onUserNotFound() {
        throw NotFoundException()
    }

    fun save(user: User): User? {
        user.setPassword(bcryptEncoder!!.encode(user.password))
        return userRepository!!.save(user)
    }
}