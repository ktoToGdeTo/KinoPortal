package com.practice.movieapp.repository;

import com.practice.movieapp.model.Favoriteactor;
import com.practice.movieapp.model.Favoritemovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteActorRepository extends JpaRepository<Favoriteactor, Long> {
    @Query("SELECT fa FROM Favoriteactor fa where fa.userportal.id = :userId")
    List<Favoriteactor> findAllFavActorsByUserId(Long userId);


}
