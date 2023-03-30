package com.hcc.dto;

import java.io.Serializable;
import java.util.Objects;

public class AssignemntResponseDto implements Serializable {
    private final Long id;
    private final String status;
    private final Integer number;
    private final String githubUrl;
    private final String branch;
    private final String reviewVideoUrl;
    private final UserDto user;
    private final UserDto codeReviewer;

    public AssignemntResponseDto(Long id, String status, Integer number, String githubUrl, String branch, String reviewVideoUrl, UserDto user, UserDto codeReviewer) {
        this.id = id;
        this.status = status;
        this.number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
        this.user = user;
        this.codeReviewer = codeReviewer;
    }

    public Long getId() {
        return id;
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

    public UserDto getUser() {
        return user;
    }

    public UserDto getCodeReviewer() {
        return codeReviewer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignemntResponseDto entity = (AssignemntResponseDto) o;
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
