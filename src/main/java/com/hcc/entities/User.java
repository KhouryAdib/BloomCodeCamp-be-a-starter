package com.hcc.entities;

import com.hcc.dto.UserDto;
import com.hcc.exceptions.NullAttributeException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cohort_start_date")
    private Date cohortStartDate;


    @Column(name = "username", unique = true, nullable=false)
    private String username;

    @Column(name = "password", nullable=false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();


    @Transient
    private String passwordConfirm;

    public User() {
    }

    public User(Long id, Date cohortStartDate, String username, String password, List<Authority> authorities) {
        this.id = id;
        this.cohortStartDate = cohortStartDate;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(UserDto userDto) {
        this.id = null;
        this.cohortStartDate = null;
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.authorities = null;
    }


    // getters and setters

    public Long getId()
    {
       // if (id == null) {throw new NullAttributeException("id is null");}
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
       // if (password == null) {throw new NullAttributeException("password is null");}
        return password;
    }

    public void setPassword(String password) {
        if (password == null) {throw new NullAttributeException("User.setPassword: password is null");}
        if (password.equals("")) {throw new IllegalArgumentException("User.setPassword: password is empty");}
        this.password = password;
    }

    public Date getCohortStartDate() {
        //if (cohortStartDate == null) {throw new NullAttributeException("User.setCohortStartDate: cohortStartDate is null");}
        return new Date(cohortStartDate.getTime());
    }

    public void setCohortStartDate(Date cohortStartDate) {
        if (cohortStartDate == null) {throw new NullAttributeException("User.setCohortStartDate: cohortStartDate is null");}
        if ( cohortStartDate.equals("")) {throw new IllegalArgumentException("User.setCohortStartDate: cohortStartDate is empty");}
        this.cohortStartDate = cohortStartDate;
    }

    public String getUsername() {
       // if (username == null) {throw new NullAttributeException("User.getUsername: username is null");}
        return username;
    }


    public void setUsername(String username) {
        if (username == null) {throw new NullAttributeException("User.setUsername: username is null");}
        if (username.equals("")) {throw new IllegalArgumentException("User.setUsername: username is empty");}
        this.username = username;
    }

    public void setAuthorities(List<Authority> authSet) {
        if (authSet == null) {throw new NullAttributeException("User.setAuthorities: authStrList is null");}
        if (authSet.isEmpty()) {throw new IllegalArgumentException("User.setAuthorities: authStrList is empty");}
        this.authorities = authSet
                .stream()
                .map(Authority::new)
                .collect(Collectors.toList());
    }

    public Set<Authority> getAuthorities() {

        if(authorities!=null) {
            return this.authorities.stream()
                    .map(Authority::new)
                    .collect(Collectors.toSet());
        }
        else {
            return null;
           // throw new NullAttributeException("User.getAuthorities: authorities should never null");
        }
    }

    public List<String> getAuthoritiesStrings() {

        // if(authorities!=null) {
        return this.authorities.stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());
        //}
        // else {
        //throw new NullAttributeException("User.getAuthorities: authorities should never null");
        //  }
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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
                    .map(n -> n.toString())
                    .collect(Collectors.joining(", ", "[", "]")) +
                '}';
    }
}
