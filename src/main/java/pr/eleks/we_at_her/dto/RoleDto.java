package pr.eleks.we_at_her.dto;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class RoleDto implements GrantedAuthority, Serializable {

    private Long id;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
