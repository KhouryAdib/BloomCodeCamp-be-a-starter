package com.hcc.services;

import com.hcc.dto.UserDto;
import com.hcc.entities.User;
import com.hcc.exceptions.EtAuthException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    User findByUsername(String username) throws UsernameNotFoundException;

    List<UserDto> findAllUsers();
}
