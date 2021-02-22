package org.oakparkoak.config.security;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import org.oakparkoak.model.ClientUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @package: org.oakparkoak.config.security
 * @author: Captain
 * @time: 2/9/2021 5:03 PM
 */
@Getter
public class ClientUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final ClientUser user;

    public ClientUserDetails(ClientUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
