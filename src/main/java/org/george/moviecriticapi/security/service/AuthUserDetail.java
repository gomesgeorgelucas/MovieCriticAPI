package org.george.moviecriticapi.security.service;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.domain.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class AuthUserDetail implements UserDetails {

    private final UserModel authUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        if (authUser == null) {
            UserModel newAuthUser = UserModel.builder().build();
            return newAuthUser.getUserPassword();
        }
        return authUser.getUserPassword();
    }

    @Override
    public String getUsername() {
        if (authUser == null) {
            UserModel newAuthUser = UserModel.builder().build();
            return newAuthUser.getUserEmail();
        }
        return authUser.getUserEmail();
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
