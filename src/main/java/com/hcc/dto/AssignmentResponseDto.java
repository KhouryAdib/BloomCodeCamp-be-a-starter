package com.hcc.dto;

import com.hcc.entities.User;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


public class AssignmentResponseDto implements Serializable {
    private  Long id;
    private  String status;
    private  Integer number;
    private  String githubUrl;
    private  String branch;
    private  String reviewVideoUrl;
    private  User user;
    private  User codeReviewer;

    public AssignmentResponseDto(Long id, String status, Integer number, String githubUrl, String branch, String reviewVideoUrl, User user, User codeReviewer) {
        this.id = id;
        this.status = status;
        this.number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
        this.user = user;
        this.codeReviewer = codeReviewer;
    }

    public AssignmentResponseDto() {

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setReviewVideoUrl(String reviewVideoUrl) {
        this.reviewVideoUrl = reviewVideoUrl;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCodeReviewer(User codeReviewer) {
        this.codeReviewer = codeReviewer;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id=id;
    }

    public String getStatus() {
        return status;
    }
    public Integer getNumber() {
        return number;
    }
    public String getGithubUrl() {
        return githubUrl;
    }
    public String getBranch() {
        return branch;
    }
    public String getReviewVideoUrl() {
        return reviewVideoUrl;
    }
    public User getUser() {
        return user;
    }
    public User getCodeReviewer() {
        return codeReviewer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentResponseDto entity = (AssignmentResponseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.number, entity.number) &&
                Objects.equals(this.githubUrl, entity.githubUrl) &&
                Objects.equals(this.branch, entity.branch) &&
                Objects.equals(this.reviewVideoUrl, entity.reviewVideoUrl) &&
                Objects.equals(this.user, entity.user) &&
                Objects.equals(this.codeReviewer, entity.codeReviewer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, number, githubUrl, branch, reviewVideoUrl, user, codeReviewer);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "status = " + status + ", " +
                "number = " + number + ", " +
                "githubUrl = " + githubUrl + ", " +
                "branch = " + branch + ", " +
                "reviewVideoUrl = " + reviewVideoUrl + ", " +
                "user = " + user + ", " +
                "codeReviewer = " + codeReviewer + ")";
    }
}
