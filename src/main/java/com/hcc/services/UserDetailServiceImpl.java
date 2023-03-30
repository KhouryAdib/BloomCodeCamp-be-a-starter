package com.hcc.services;

 import com.hcc.entities.User;
 import com.hcc.exceptions.EtAuthException;
 import com.hcc.exceptions.ResourceNotFoundException;
 import com.hcc.repositories.UserRepository;
 import com.hcc.utils.CustomPasswordEncoder;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Service;

 import javax.transaction.Transactional;
 import java.util.Date;
 import java.util.Optional;
 import java.util.regex.Pattern;



@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    CustomPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepo.findByUsername(username);

        User user = userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
        user.setPassword(passwordEncoder.getPasswordEncoder().encode("asdfasdf"));

        return new UserDetailsImpl(user);
    }


 }
