package ir.smartech.cro.storage.config.security

import ir.smartech.cro.storage.data.postgres.entity.Client
import ir.smartech.cro.storage.data.postgres.repository.ClientRepository
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
    private val clientRepository: ClientRepository? = null

    override fun loadUserByUsername(username: String): UserDetails {
        val user = clientRepository?.findByUsername(username)
        if (user == null)
            onUserNotFound()
        return user!!
    }

    fun getCurrentClient(): Client? {
        val currentClient: Optional<String?>? = SecurityUtils.getCurrentClientLogin()
        if (currentClient?.isEmpty == true) {
            onUserNotFound()
        }
        val currentUserName: String = currentClient?.get()!!
        val client: Client? = clientRepository!!.findByUsername(currentUserName)
        if (client == null) {
            onUserNotFound()
        }
        return client
    }

    private fun onUserNotFound() {
        throw NotFoundException()
    }

    fun save(client: Client): Client? {
        client.password = bcryptEncoder!!.encode(client.password!!)
        return clientRepository!!.save(client)
    }
}