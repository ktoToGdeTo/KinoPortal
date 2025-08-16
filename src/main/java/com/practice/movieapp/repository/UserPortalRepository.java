package com.practice.movieapp.repository;

import com.practice.movieapp.model.Userportal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPortalRepository extends JpaRepository<Userportal, Long> {
    Optional<Userportal> findByUsername(String username);


}
