package com.hcc.entities;

import com.hcc.exceptions.NullAttributeException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cohort_start_date", nullable = false)
    private Date cohortStartDate;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "authority")
    private List<Authority> authorities;

    public User() {
    }

    public User(long user_id, Date cohortStartDate, String username, String password, List<String> authorities) {
        setId(user_id);
        setCohortStartDate(new Date(cohortStartDate.getTime()));
        setUsername(username);
        setPassword(password);
        setAuthorities(new ArrayList<>(authorities));
    }

    // getters and setters

    public Long getId()
    {
        if (id == null) {throw new NullAttributeException("id is null");}
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        if (password == null) {throw new NullAttributeException("password is null");}
        return password;
    }

    public void setPassword(String password) {
        if (password == null) {throw new NullAttributeException("User.setPassword: password is null");}
        if (password.equals("")) {throw new IllegalArgumentException("User.setPassword: password is empty");}
        this.password = password;
    }

    public Date getCohortStartDate() {
        if (cohortStartDate == null) {throw new NullAttributeException("User.setCohortStartDate: cohortStartDate is null");}
        return new Date(cohortStartDate.getTime());
    }

    public void setCohortStartDate(Date cohortStartDate) {
        if (cohortStartDate == null) {throw new NullAttributeException("User.setCohortStartDate: cohortStartDate is null");}
        if ( cohortStartDate.equals("")) {throw new IllegalArgumentException("User.setCohortStartDate: cohortStartDate is empty");}
        this.cohortStartDate = cohortStartDate;
    }

    public String getUsername() {
        if (username == null) {throw new NullAttributeException("User.getUsername: username is null");}
        return username;
    }

    public void setUsername(String username) {
        if (username == null) {throw new NullAttributeException("User.setUsername: username is null");}
        if (username.equals("")) {throw new IllegalArgumentException("User.setUsername: username is empty");}
        this.username = username;
    }

    public void setAuthorities(List<String> authStrList) {
        if (authStrList == null) {throw new NullAttributeException("User.setAuthorities: authStrList is null");}
        if (authStrList.isEmpty()) {throw new IllegalArgumentException("User.setAuthorities: authStrList is empty");}
        this.authorities = authStrList.stream()
                .map(Authority::new)
                .collect(Collectors.toList());
    }

    public List<Authority> getAuthorities() {

        if(authorities!=null) {
            return this.authorities.stream()
                    .map(Authority::new)
                    .collect(Collectors.toList());
        }
        else {
            throw new NullAttributeException("User.getAuthorities: authorities should never null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getCohortStartDate(), user.getCohortStartDate()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(new TreeSet<>(getAuthorities()), new TreeSet<>(user.getAuthorities()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCohortStartDate(), getUsername(), getPassword(), getAuthorities());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cohortStartDate=" + cohortStartDate +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities.stream()
                    .map(n -> n.getAuthority())
                    .collect(Collectors.joining(", ", "[", "]")) +
                '}';
    }
}
