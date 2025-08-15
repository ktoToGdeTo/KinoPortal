package com.practice.movieapp.repository;

import com.practice.movieapp.model.Actorlist;
import com.practice.movieapp.model.ActorlistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorlistRepository extends JpaRepository<Actorlist, ActorlistId> {

    @Query("SELECT al FROM Actorlist al where al.actor.actorId = :actorId")
    List<Actorlist> findActorRolesByActorId(@Param("actorId") Long actorId);

    @Query("SELECT al FROM Actorlist al where al.movie.movieId = :movieId")
    List<Actorlist> findCastByMovieId(@Param("movieId") Long movieId);

}