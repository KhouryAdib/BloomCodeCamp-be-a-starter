package com.hcc.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AssignmentTest {

    private Assignment assignment;

    @BeforeEach
    public void setup() {
        assignment = new Assignment();
    }
    @Test
    public void testGetId_ValidInput_ShouldSetAndReturnId() {
        Long id = 1L;
        assertNull(assignment.getId());
        assignment.setId(id);
        assertEquals(id, assignment.getId());
    }

    @Test
    public void testGetStatus_ValidInput_ShouldSetAndReturnStatus() {
        String status = "submitted";
        assertNull(assignment.getStatus());
        assignment.setStatus(status);
        assertEquals(status, assignment.getStatus());
    }

    @Test
    public void testGetNumber_ValidInput_ShouldSetAndReturnNumber() {
        Integer number = 1;
        assertNull(assignment.getNumber());
        assignment.setNumber(number);
        assertEquals(number, assignment.getNumber());
    }

    @Test
    public void testGetGithubUrl_ValidInput_ShouldSetAndReturnGithubUrl() {
        String githubUrl = "https://github.com/test";
        assertNull(assignment.getGithubUrl());
        assignment.setGithubUrl(githubUrl);
        assertEquals(githubUrl, assignment.getGithubUrl());
    }

    @Test
    public void testGetBranch_ValidInput_ShouldSetAndReturnBranch() {
        String branch = "main";
        assertNull(assignment.getBranch());
        assignment.setBranch(branch);
        assertEquals(branch, assignment.getBranch());
    }

    @Test
    public void testGetReviewVideoUrl_ValidInput_ShouldSetAndReturnReviewVideoUrl() {
        String reviewVideoUrl = "https://youtu.be/dQw4w9WgXcQ";
        assertNull(assignment.getReviewVideoUrl());
        assignment.setReviewVideoUrl(reviewVideoUrl);
        assertEquals(reviewVideoUrl, assignment.getReviewVideoUrl());
    }

    @Test
    public void testGetUser_ValidInput_ShouldSetAndReturnUser() {
        User user = new User();
        assertNull(assignment.getUser());
        assignment.setUser(user);
        assertEquals(user, assignment.getUser());
    }

    @Test
    public void testGetCodeReviewer_ValidInput_ShouldSetAndReturnCodeReviewer() {
        User codeReviewer = new User();
        assertNull(assignment.getCodeReviewer());
        assignment.setCodeReviewer(codeReviewer);
        assertEquals(codeReviewer, assignment.getCodeReviewer());
    }

    @Test
    public void testEquals_SameObject_ShouldReturnTrue() {
        assertEquals(assignment, assignment);
    }

}
