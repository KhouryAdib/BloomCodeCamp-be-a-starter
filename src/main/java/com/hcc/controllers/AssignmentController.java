package com.hcc.controllers;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.dto.UserDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import com.hcc.services.AssignmentServiceImpl;
import com.hcc.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
//@RequestMapping("")
public class AssignmentController {


        private AssignmentServiceImpl assignmentService;
        private UserServiceImpl userService;

        @Autowired
        public AssignmentController(AssignmentServiceImpl assignmentService,UserServiceImpl userService) {
            this.assignmentService = assignmentService;
            this.userService=userService;
        }

        // Handler method to get all assignments for a specific user
        @GetMapping("/api/assignments")
 //       public List<Assignment> getAssignmentsByUser() {
        public String getAssignmentsByUser(Model model) {
            // Get the authentication object from the security context holder
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Get the principal object from the authentication object
            Object principal = authentication.getPrincipal();

            User user;

            // Check if the principal is an instance of UserDetails, which contains the username
            if (principal instanceof UserDetails) {

                String username = ((UserDetails)principal).getUsername();
                user = userService.findByUsername(username);
                System.out.println("Getting assignments for " + user.getUsername());
                List<Assignment> assignments =  assignmentService.getAssignmentsByUser(user);
                model.addAttribute("assignments", assignments);

            } else {
                throw new RuntimeException("User not authenticated");
            }

            //return assignmentService.getAssignmentsByUser(user);
            return "/api/assignments";
        }

        // Handler method to get a specific assignment by ID
        @GetMapping("/api/assignments/{id}")
        public String getAssignmentById(@PathVariable("id") Long id, Model model) {
            System.out.println("Getting assignment by id " + id);
            Assignment assignment = assignmentService.getAssignmentById(id);
            model.addAttribute("assignment", assignment);
            return "api/addassignment";
        }

        // Handler method to update an assignment by ID
        @PutMapping("/api/assignments/{id}")
        public ResponseEntity<Void> updateAssignmentById(@PathVariable("id") Long id, @Valid @RequestBody AssignmentResponseDto assignmentDto,
                                                         BindingResult result) {
            if (result.hasErrors()) {
                return ResponseEntity.badRequest().build();
            }

            assignmentService.updateAssignmentById(id, assignmentDto);

            return ResponseEntity.ok().build();
        }


        // handler method to handle user registration form request
        @GetMapping("api/addassignment")
        public String showAddAssignmentForm(Model model) {
            // create model object to store form data
            AssignmentResponseDto assignment = new AssignmentResponseDto();
            model.addAttribute("assignment", assignment);
            return "api/addassignment";
        }

        // Handler method to create a new assignment
        @PostMapping("/api/assignments/save")
        public RedirectView createAssignment(@Valid @ModelAttribute("assignment") AssignmentResponseDto assignmentDto,
                                             @NotNull BindingResult result,
                                             Model model) {

        if (result.hasErrors()) {
            System.out.println("result has errors");
            model.addAttribute("assignment", assignmentDto);
            return new RedirectView("/api/assignments");
        }

        //assignmentDto.setId(null); // ensure new assignment is created

        System.out.println("trying to save new assignment");
        assignmentService.save(assignmentDto);
        System.out.println("assignment added successfully");


        return new RedirectView("/api/assignments");
        }
    }
