package com.hcc.services;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.dto.UserDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;

import java.util.List;

public interface AssignmentService {



    Assignment getAssignmentById(Long id);
    void putAssignmentsById(Long id, AssignmentResponseDto assignmentResponseDto);
    void postAssignment(AssignmentResponseDto assignment);



    List<AssignmentResponseDto> findAllAssignments();

    void updateAssignmentById(Long id, AssignmentResponseDto assignmentDto);

    void save(AssignmentResponseDto assignmentDto);

    List<Assignment> getAssignmentsByUser(User user);
}
