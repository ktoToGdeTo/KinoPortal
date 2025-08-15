package com.practice.movieapp.mapper;

import com.practice.movieapp.dto.MovieDto;
import com.practice.movieapp.model.Movie;

public class MovieMapper {

    public static MovieDto mapToMovieDto(Movie movie){
        return new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getDescription(),
                movie.getDaterelease(),
                movie.getDirector());

    }

}
