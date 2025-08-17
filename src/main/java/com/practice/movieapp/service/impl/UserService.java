package com.practice.movieapp.service.impl;

import com.practice.movieapp.model.Favoriteactor;
import com.practice.movieapp.model.Favoritemovie;
import com.practice.movieapp.model.Userportal;
import com.practice.movieapp.repository.FavoriteActorRepository;
import com.practice.movieapp.repository.FavoriteMovieRepository;
import com.practice.movieapp.repository.UserPortalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private UserPortalRepository userPortalRepository;
    private FavoriteMovieRepository favoriteMovieRepository;
    private FavoriteActorRepository favoriteActorRepository;

    public List<Userportal> getAllUsers() { return userPortalRepository.findAll(); }

    @Override
    public Userportal loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPortalRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void registerUser(Userportal user){
        userPortalRepository.save(user);
    }

    public boolean checkUser(String username){
        return userPortalRepository.existsUserportalByUsername(username);
    }
    public List<Favoritemovie> getFavMovies(Long id){
        return favoriteMovieRepository.findAllFavMoviesByUserId(id);
    }

    public List<Favoriteactor> getFavActors(Long id){
        return favoriteActorRepository.findAllFavActorsByUserId(id);
    }

    public boolean isFavoriteActor(Long userId, Long actorId){
        return favoriteActorRepository.existsActorByUserportal_IdAndActor_ActorId(userId, actorId);
    }

    public void deleteFavoriteActor(Long userId, Long actorId){
        favoriteActorRepository.deleteByUserportal_IdAndActor_ActorId(userId, actorId);
    }

    public void addFavoriteActor(Favoriteactor favoriteactor){
        favoriteActorRepository.save(favoriteactor);
    }

    public boolean isFavoriteMovie(Long userId, Long movieId){
        return favoriteMovieRepository.existsActorByUserportal_IdAndMovie_MovieId(userId, movieId);
    }

    public void deleteFavoriteMovie(Long userId, Long movieId){
        favoriteMovieRepository.deleteByUserportal_IdAndMovie_MovieId(userId, movieId);
    }

    public void addFavoriteMovie(Favoritemovie favoritemovie){
        favoriteMovieRepository.save(favoritemovie);
    }


}
