package com.hcc.resources;


import com.hcc.entities.User;
import com.hcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {

        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        User user = userService.registerUser(new Date(Long.MAX_VALUE),username,password);

        Map<String, String> map = new HashMap<>();
        map.put("message", "registered successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
