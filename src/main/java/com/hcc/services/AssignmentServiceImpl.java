package com.hcc.services;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.dto.UserDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService{

    private AssignmentRepository assignmentRepo;
    private UserServiceImpl userService;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepo, UserServiceImpl userService){
        this.assignmentRepo=assignmentRepo;
        this.userService = userService;
    }


    @Override
    public Assignment getAssignmentById(Long id) {
        Optional<Assignment> optionalAssignment = assignmentRepo.findById(id);
        if (optionalAssignment.isPresent()) {
            return optionalAssignment.get();
        } else {
            throw new EntityNotFoundException("Assignment not found with id: " + id);
        }
    }

    @Override
    public void putAssignmentsById(Long id, AssignmentResponseDto assignmentResponseDto) {
        // Retrieve the existing Assignment entity from the database
        Optional<Assignment> optionalAssignment = assignmentRepo.findById(id);
        if (!optionalAssignment.isPresent()) {
            // Throw an exception or return an appropriate response if the Assignment is not found
            throw new EntityNotFoundException("Assignment not found");
        }
        Assignment assignment = optionalAssignment.get();

        User user = userService.findByUsername(assignmentResponseDto.getUser().getUsername());
        assignment.setUser(user);

        User codeReviewer = userService.findByUsername(assignmentResponseDto.getUser().getUsername());
        assignment.setCodeReviewer(codeReviewer);

        // Map the fields from the assignmentResponseDto to the corresponding fields in the Assignment entity
        assignment.setStatus(assignmentResponseDto.getStatus());
        assignment.setNumber(assignmentResponseDto.getNumber());
        assignment.setGithubUrl(assignmentResponseDto.getGithubUrl());
        assignment.setBranch(assignmentResponseDto.getBranch());
        assignment.setReviewVideoUrl(assignmentResponseDto.getReviewVideoUrl());

        // Save the updated Assignment entity back to the database
        assignmentRepo.save(assignment);
    }

    @Override
    public void postAssignment(AssignmentResponseDto assignment) {

    }

    @Override
    public List<AssignmentResponseDto> findAllAssignments() {
        System.out.println("AssignmentServiceImpl.findAllAssignments ");
        List<Assignment> users = assignmentRepo.findAll();
        return users.stream()
                .map((assignment) -> mapToAssignmentDto(assignment))
                .collect(Collectors.toList());
    }

    @Override
    public void updateAssignmentById(Long id, AssignmentResponseDto assignmentDto) {

    }

    @Override
    public void save(AssignmentResponseDto assignmentDto) {
        System.out.println("AssignmentServiceImpl.save: adding or Updating "+ assignmentDto.toString());
        Assignment assignment = new Assignment();
        assignment.setId(assignmentDto.getId());
        assignment.setReviewVideoUrl(assignmentDto.getReviewVideoUrl());
        assignment.setBranch(assignmentDto.getBranch());
        assignment.setGithubUrl(assignmentDto.getGithubUrl());
        assignment.setStatus("Submitted");
        assignment.setNumber(assignmentDto.getNumber());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        assignment.setUser(user);

       // assignment.setUser(assignmentDto.getUser());
        assignment.setCodeReviewer(assignmentDto.getCodeReviewer());

        assignmentRepo.save(assignment);
    }

    @Override
    public List<Assignment> getAssignmentsByUser(User user) {
        System.out.println("AssignmentServiceImpl.getAssignmentsByUser " + user.getUsername());
        List<Assignment> assignments = assignmentRepo.findByUser(user);
        return assignments;
    }

    private AssignmentResponseDto mapToAssignmentDto(Assignment assignment){
        System.out.println("AssignmentServiceImpl.mapToUserDto ");
        AssignmentResponseDto assignmentDto = new AssignmentResponseDto(
                assignment.getId(),
                assignment.getStatus(),
                assignment.getNumber(),
                assignment.getGithubUrl(),
                assignment.getBranch(),
                assignment.getReviewVideoUrl(),
                assignment.getUser(),
                assignment.getCodeReviewer()
        );
        return assignmentDto;
    }

}
