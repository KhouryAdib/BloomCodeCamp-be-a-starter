package com.hcc.dto;

import java.io.Serializable;
import java.util.Objects;

public class AuthCredentialRequest implements Serializable {
    private final Long id;
    private final String authority;
    private final UserDto user;

    public AuthCredentialRequest(Long id, String authority, UserDto user) {
        this.id = id;
        this.authority = authority;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    public UserDto getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthCredentialRequest entity = (AuthCredentialRequest) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.authority, entity.authority) &&
                Objects.equals(this.user, entity.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority, user);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "authority = " + authority + ", " +
                "user = " + user + ")";
    }
}
