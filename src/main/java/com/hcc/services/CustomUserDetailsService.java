package com.hcc.services;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loading UserByUsername");
        User user = userRepository.findByUsername(username).orElseThrow(() ->
        {
            throw new UsernameNotFoundException("CustomUserDetailsService.loadUserByUsername: username not found");
        });

        UserDetails out = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getAuthorities()));


        System.out.println("CustomUserDetailsService.loadUserByUsername| " +
                " | username : "+ user.getUsername() +
                " | password : "+ user.getPassword() +
                " | auth     : ");
        user.getAuthorities().stream().forEach(auth -> System.out.println(auth.getAuthority()));

            return out;

    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Authority> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }


}