package com.hcc.services;

import com.hcc.entities.User;
import com.hcc.exceptions.EtAuthException;
import com.hcc.exceptions.ResourceNotFoundException;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    CustomPasswordEncoder passwordEncoder;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        System.out.println("validateUser");
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public User getUserById(int uId) {
        return  userRepo.findById(uId).orElse(null);
    }

    @Override
    public User registerUser(Date date, String username, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        //check if email in correct format
        if(username != null){
            username = username.toLowerCase();
        }
        else {
            throw new EtAuthException("Invalid username Format");
        }

        //check if email already in use

        Integer count = userRepo.getCountByUsername(username);
        if(count>0) {throw new EtAuthException("Email already in use");}
        Integer userId = userRepo.create(date, username, password);

        User user = userRepo.findByUserId(userId.longValue()).orElse(null);
        if(user!=null) {
            return user;
        } else{
            throw new ResourceNotFoundException("Cannot find Username");
        }
    }

    @Override
    public User addOrUpdateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User deleteUser(int uId) throws UsernameNotFoundException{
        User deletedUser = null;

            deletedUser = userRepo.findById(uId).orElse(null);
            if(deletedUser == null) {
                throw new UsernameNotFoundException("User not Found");
            }
            else {
               userRepo.deleteById((long) uId);
            }

        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepo.findByUsername(username);
        User user = new User();

        if(userOpt.isPresent())
        {
            user = userOpt.get();
        }
        else
        {
            throw new UsernameNotFoundException("Invalid Credentials");
        }

     //   user.setUsername(username);
     //   user.setPassword(passwordEncoder.getPasswordEncoder().encode("asdfasdf"));

        return new UserDetailsImpl(user);
    }
}
