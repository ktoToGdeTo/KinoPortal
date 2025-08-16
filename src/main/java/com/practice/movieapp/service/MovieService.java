package com.practice.movieapp.service;

import com.practice.movieapp.dto.MovieDto;
import com.practice.movieapp.model.Actorlist;
import com.practice.movieapp.model.Movie;

import java.util.List;

public interface MovieService {

    MovieDto getMovieDtoById(Long movieId);

    Movie getMovieById(Long movieId);

    List<MovieDto> getAllMovies();

    List<MovieDto> foundMovies(String title);

}
