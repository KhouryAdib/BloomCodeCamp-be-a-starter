package com.hcc.services;

import com.hcc.dto.UserDto;
import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private UserRepository userRepo;


    private AuthorityRepository authRepo;


    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, AuthorityRepository authRepo, PasswordEncoder  passwordEncoder) {
        this.userRepo = userRepo;
        this.authRepo = authRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(UserDto userDto) {
        System.out.println("UserServiceImpl.save: adding or Updating "+ userDto.toString());
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Authority authority = new Authority();

        //Create an admin role if it doesn't exist.

            authority = authRepo.findByAuthority("ADMIN");
            if (authority == null) {
                authority = new Authority("ADMIN" , user);
                authRepo.save(authority);
            }


        user.setAuthorities(Arrays.asList(authority));
        user.setCohortStartDate(new Date());


        userRepo.save(user);
    }

    private Authority checkRoleExist(){
        Authority role = authRepo.findByAuthority("ADMIN");
        if(role == null){
            role = new Authority();
            role.setAuthority("ADMIN");
            authRepo.save(role);
        }
        return role;
    }

    @Override
    public User findByUsername(String username) {
        System.out.println("UserServiceImpl.findByUsername "+ username);
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public List<UserDto> findAllUsers() {
        System.out.println("UserServiceImpl.findAllUsers ");
        List<User> users = userRepo.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        System.out.println("UserServiceImpl.mapToUserDto ");
        UserDto userDto = new UserDto(user.getUsername(),user.getPassword());
        return userDto;
    }
}
