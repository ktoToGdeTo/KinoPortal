package com.practice.movieapp.service.impl;

import com.practice.movieapp.dto.MovieDto;
import com.practice.movieapp.exceptions.ResourceNotFoundExcepetion;
import com.practice.movieapp.mapper.MovieMapper;
import com.practice.movieapp.model.Actorlist;
import com.practice.movieapp.model.Movie;
import com.practice.movieapp.repository.ActorRepository;
import com.practice.movieapp.repository.ActorlistRepository;
import com.practice.movieapp.repository.MovieRepository;
import com.practice.movieapp.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {


    private final MovieRepository movieRepository;


    @Override
    public MovieDto getMovieDtoById(Long movieId) {
        Movie foundMovie =  movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundExcepetion("Movie with ID " + movieId + " not found."));
        return MovieMapper.mapToMovieDto(foundMovie);
    }

    @Override
    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundExcepetion("Movie with ID " + movieId + " not found."));
    }


//    @Override
//    public MovieDto addMovie(MovieDto movieDto) {
//        Movie movie = MovieMapper.mapToMovie(movieDto);
//        Movie savedMovie = movieRepository.save(movie);
//
//        return MovieMapper.mapToMovieDto(savedMovie);
//    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAllByOrderByTitleAsc();
        return movies.stream().map((movie) -> MovieMapper.mapToMovieDto(movie))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> foundMovies(String title) {
        String searchTitle = title.replaceAll("\\s+", " ");
        List<Movie> movies = movieRepository.findAllByTitleContainsIgnoreCase(searchTitle.trim());
        return movies.stream().map((movie) -> MovieMapper.mapToMovieDto(movie))
                .collect(Collectors.toList());
    }

}
