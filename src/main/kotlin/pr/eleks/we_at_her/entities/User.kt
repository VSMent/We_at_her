package pr.eleks.we_at_her.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import pr.eleks.we_at_her.dto.RoleDto
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "usr")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        @Column(nullable = false, unique = true)
        private var username: String,
        private var password: String,
        var cityId: Long,
        var email: String,
        @Enumerated(EnumType.STRING)
        @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
        @Basic
        var role: RoleDto,
        var activated: Boolean,
        @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)")
        var uuid: UUID
) : UserDetails {
    fun setUsername(u: String) {
        username = u
    }

    fun setPassword(p: String) {
        password = p
    }

    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun getAuthorities(): Collection<GrantedAuthority> = listOf(role)
    override fun isEnabled(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
}