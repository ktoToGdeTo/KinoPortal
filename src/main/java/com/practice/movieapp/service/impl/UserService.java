package com.practice.movieapp.service.impl;

import com.practice.movieapp.model.Favoriteactor;
import com.practice.movieapp.model.Favoritemovie;
import com.practice.movieapp.model.Userportal;
import com.practice.movieapp.repository.FavoriteActorRepository;
import com.practice.movieapp.repository.FavoriteMovieRepository;
import com.practice.movieapp.repository.UserPortalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
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

    public List<Favoritemovie> getFavMovies(Long id){
        return favoriteMovieRepository.findAllFavMoviesByUserId(id);
    }

    public List<Favoriteactor> getFavActors(Long id){
        return favoriteActorRepository.findAllFavActorsByUserId(id);
    }


}
