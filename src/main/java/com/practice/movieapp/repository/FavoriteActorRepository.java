package com.practice.movieapp.repository;

import com.practice.movieapp.model.Favoriteactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteActorRepository extends JpaRepository<Favoriteactor, Long> {
    @Query("SELECT fa FROM Favoriteactor fa where fa.userportal.id = :userId")
    List<Favoriteactor> findAllFavActorsByUserId(Long userId);

    boolean existsActorByUserportal_IdAndActor_ActorId(Long userId, Long actorId);

    void deleteByUserportal_IdAndActor_ActorId(Long userId, Long actorId);


}
