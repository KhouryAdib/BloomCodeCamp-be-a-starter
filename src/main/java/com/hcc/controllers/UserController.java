package com.hcc.controllers;


import com.hcc.entities.User;
import com.hcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //for testing, delete later.
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("getting all users");
        List<User> users = null;

        try{
            users = userService.getAllUsers();
        }catch(Exception e) {
            e.getMessage();
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable("id") int uId) {
        System.out.println("getting user with id: "+ uId);
        User user = null;

        try{
            user = userService.getUserById(uId);
        }catch(Exception e) {
            e.getMessage();
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/addOrUpdate")
    public ResponseEntity<User> addOrUpdate(@RequestBody User user) {
        System.out.println("Creating user: "+ user.getUsername());
        try{
            user = userService.addOrUpdateUser(user);
        }catch(Exception e) {
            e.getMessage();
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
