package com.hcc.entities;

import com.hcc.enums.AuthorityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority, Comparable<Authority> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String authority;

    @ManyToOne()
    @JoinColumn(name = "username")
    private User user;

    public Authority(){}

    public Authority(String auth, User user){
        setAuthority(auth);
    }

    public Authority(Authority authority) {
        setAuthority(authority.getAuthority());
        setUser(authority.getUser());
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compareTo(Authority other) {
        return this.getAuthority().compareTo(other.getAuthority());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority auth = (Authority) o;
        return Objects.equals(getAuthority(), auth.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthority());
    }
}