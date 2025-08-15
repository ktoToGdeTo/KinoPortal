package com.practice.movieapp.repository;

import com.practice.movieapp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByTitleContainsIgnoreCase(String title);

    @Query("SELECT al.movie FROM Actorlist al where al.actor.actorId = :actorId")
    List<Movie> findMoviesByActorId(@Param("actorId") Long actorId);

}
