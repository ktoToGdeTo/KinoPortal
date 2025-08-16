package com.practice.movieapp.repository;

import com.practice.movieapp.model.Favoritemovie;
import com.practice.movieapp.model.FavoritemovieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteMovieRepository extends JpaRepository<Favoritemovie, FavoritemovieId> {
    @Query("SELECT fm FROM Favoritemovie fm where fm.userportal.id = :userId")
    List<Favoritemovie> findAllFavMoviesByUserId(Long userId);

}
