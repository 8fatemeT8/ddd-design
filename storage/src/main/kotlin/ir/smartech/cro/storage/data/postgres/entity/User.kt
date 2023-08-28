package ir.smartech.cro.storage.data.postgres.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class User : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String? = null

    @Column(unique = true)
    private var username: String? = null
    override fun getUsername(): String? {
        return username!!
    }
    fun setUsername(value: String?){
        username = value
    }

    private var password: String? = null
    override fun getPassword(): String? {
        return password
    }
    fun setPassword(value: String?){
        password = value
    }

    var topicName: String? = null
        get() = "${name}_topic"

    @OneToOne
    var projectSchema: ProjectSchema? = null
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
       return arrayListOf()
    }


    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}