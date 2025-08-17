package com.practice.movieapp.repository;

import com.practice.movieapp.model.Actor;
import com.practice.movieapp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findAllByFullnameContainsIgnoreCase(String name);

    List<Actor> findAllByOrderByFullnameAsc();
}
