package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.exceptions.ResourceNotFoundException;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;

    // Login endpoint
    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
            return ResponseEntity.ok("Login successful");
    }

    // Token validation endpoint
    @PostMapping("/auth/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        // public ResponseEntity<?> validateToken() {
        return ResponseEntity.ok("Token is valid");
    }

    // Get all assignments for a user endpoint
    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> getAllAssignmentsByUser(@RequestParam(value = "userId") Long userId) {
        List<Assignment> assignments = assignmentRepository.findByUserId(userId);
        return ResponseEntity.ok(assignments);
    }

    // Get a single assignment by ID endpoint
    @GetMapping("/assignments/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable(value = "id") Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment id: "+ assignmentId));
        return ResponseEntity.ok(assignment);
    }

    // Update a single assignment by ID endpoint
    public ResponseEntity<Assignment> updateAssignmentById(@PathVariable(value = "id") Long assignmentId,
                                                           @Valid @RequestBody Assignment assignmentDetails) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment id: " + assignmentId));
        assignment.setBranch(assignmentDetails.getBranch());
        assignment.setReviewVideoUrl(assignmentDetails.getReviewVideoUrl());
        assignment.setGithubUrl(assignmentDetails.getGithubUrl());
        assignment.setNumber(assignmentDetails.getNumber());
        assignment.setUser(assignmentDetails.getUser());
        assignment.setCodeReviewer(assignmentDetails.getCodeReviewer());
        Assignment updatedAssignment = assignmentRepository.save(assignment);
        return ResponseEntity.ok(updatedAssignment);
    }

   // Create a new assignment endpoint
    @PostMapping("/assignments")
    public ResponseEntity<Assignment> createAssignment(@Valid @RequestBody Assignment assignment) {
        Assignment newAssignment = assignmentRepository.save(assignment);
        return ResponseEntity.ok(newAssignment);
    }
}