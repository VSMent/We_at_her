package pr.eleks.we_at_her.dto;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public enum  RoleDto implements GrantedAuthority, Serializable {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
