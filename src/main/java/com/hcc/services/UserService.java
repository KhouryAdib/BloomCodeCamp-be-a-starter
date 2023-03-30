package com.hcc.services;

import com.hcc.entities.User;
import com.hcc.exceptions.EtAuthException;

import java.util.Date;
import java.util.List;

public interface UserService {

    public User validateUser(String username, String password) throws EtAuthException;
    public List<User> getAllUsers();
    public User getUserById(int uId);
    public User registerUser(Date cohort_start_date, String username, String password) throws EtAuthException;
    public User addOrUpdateUser(User user);
    public User deleteUser(int uId);
}
