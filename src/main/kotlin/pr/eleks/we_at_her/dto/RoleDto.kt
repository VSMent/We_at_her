package pr.eleks.we_at_her.dto

import org.springframework.security.core.GrantedAuthority

enum class RoleDto : GrantedAuthority {
    USER, ADMIN;

    override fun getAuthority(): String = name
}
