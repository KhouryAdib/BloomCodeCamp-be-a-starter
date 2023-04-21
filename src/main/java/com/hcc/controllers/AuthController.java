package com.hcc.controllers;
import com.hcc.dto.UserDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import com.hcc.services.AssignmentServiceImpl;
import com.hcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {


    private UserService userService;

    private AssignmentServiceImpl assignmentService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){


        User existingUser = userService.findByUsername(userDto.getUsername());

        if(existingUser != null){
            result.rejectValue("username", null,
                    "There is already an account registered with the same username");
        }

        if(result.hasErrors()){
            System.out.println("result has errors");
            model.addAttribute("user", userDto);
            return "register";
        }
        System.out.println("trying to save new user");
        userService.save(userDto);
        System.out.println("user registered successfully");

        model.addAttribute("success", true);

        return "register";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }


}