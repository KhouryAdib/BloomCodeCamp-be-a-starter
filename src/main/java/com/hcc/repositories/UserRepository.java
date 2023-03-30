package com.hcc.repositories;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.exceptions.EtAuthException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Integer create(Date cohort_start_date, String username, String password) throws EtAuthException;
    Optional<User> findByUsernameAndPassword(String username, String password) throws EtAuthException;
    Integer getCountByUsername(String username);

    Optional<User> findByUserId(Long userId);
    Optional<User> findByUsername(String username);

    Optional<User> findById(int uId);
}