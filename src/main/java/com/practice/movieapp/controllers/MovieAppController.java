package com.practice.movieapp.controllers;

import com.practice.movieapp.dto.ActorDto;
import com.practice.movieapp.dto.MovieDto;
import com.practice.movieapp.model.Actorlist;
import com.practice.movieapp.model.Movie;
import com.practice.movieapp.service.ActorListService;
import com.practice.movieapp.service.ActorService;
import com.practice.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class MovieAppController {

    private final MovieService movieService;
    private final ActorService actorService;
    private final ActorListService actorListService;

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/search")
    public String search(@RequestParam("text") String text, Model model){
        List<MovieDto> foundMovies = movieService.foundMovies(text);
        List<ActorDto> foundActors = actorService.foundActors(text);
        model.addAttribute("foundActors", foundActors);
        model.addAttribute("foundMovies", foundMovies);
        System.out.println("you want search" + text);
        return "search";
    }

    @GetMapping("/movies")
    public String movies(Model model){
        List<MovieDto> allMovies = movieService.getAllMovies();
        model.addAttribute("allMovies", allMovies);
        return "movies";
    }

    @GetMapping("/actors")
    public String actors(Model model){
        List<ActorDto> allActors = actorService.getAllActors();
        model.addAttribute("allActors", allActors);
        return "actors";
    }

//    @PostMapping("/addMovie")
//    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto){
//        MovieDto savedMovie = movieService.addMovie(movieDto);
//        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
//    }

    @GetMapping(value = "/movies/{id}")
    public String movieById(@PathVariable(name="id") Long id, Model model){
        MovieDto movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        List<Actorlist> roles = actorListService.getActorsByMovieId(id);
        model.addAttribute("roles", roles);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("dateRelease", sdf.format(movie.getDateRelease()));
        return "moviePage";
    }

    @GetMapping(value = "/actors/{id}")
    public String actorById(@PathVariable(name="id") Long id, Model model){
        ActorDto actor = actorService.getActorById(id);
        model.addAttribute("actor", actor);
        List<Actorlist> roles = actorListService.getMoviesByActorId(id);
        model.addAttribute("roles", roles);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("birthDate", sdf.format(actor.getBirthdate()));
        return "actorPage";
    }

}
