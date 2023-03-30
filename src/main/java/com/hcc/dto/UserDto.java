package com.hcc.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {
    private final String username;

    public UserDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.username, entity.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "username = " + username + ")";
    }
}
