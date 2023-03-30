package com.hcc.services;

import com.hcc.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return the user's authorities/roles
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // return true if the user's account is not expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // return true if the user's account is not locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // return true if the user's credentials are not expired
        return true;
    }

    @Override
    public boolean isEnabled() {
        //always returns true because this functionality is not in the design doc.
        return true;
    }
}
