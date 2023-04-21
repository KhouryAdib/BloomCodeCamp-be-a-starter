package com.hcc.repositories;


import com.hcc.entities.Authority;
import com.hcc.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
    Authority findByAuthority(String authority);
}