package com.practice.movieapp.controllers;

import com.practice.movieapp.dto.ActorDto;
import com.practice.movieapp.dto.MovieDto;
import com.practice.movieapp.model.Actorlist;
import com.practice.movieapp.model.Movie;
import com.practice.movieapp.model.Userportal;
import com.practice.movieapp.service.ActorListService;
import com.practice.movieapp.service.ActorService;
import com.practice.movieapp.service.MovieService;
import com.practice.movieapp.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class MovieAppController {

    private final MovieService movieService;
    private final ActorService actorService;
    private final ActorListService actorListService;
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

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


    @GetMapping(value = "/movies/{id}")
    public String movieById(@PathVariable(name="id") Long id, Model model){

        Movie movie = movieService.getMovieById(id);
        Userportal user = getCurrentUser();

        boolean isFav = false;


        MovieDto movieDto = movieService.getMovieDtoById(id);
        model.addAttribute("movie", movieDto);
        List<Actorlist> roles = actorListService.getActorsByMovieId(id);
        model.addAttribute("roles", roles);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("dateRelease", sdf.format(movieDto.getDateRelease()));
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



    private Userportal getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.loadUserByUsername(auth.getName());
    }

//    @PostMapping(value = "/actors/{id}/toggle")
//    public String toggleFav(
//            @PathVariable(name = "id") Long id,
//            Authentication auth,
//            RedirectAttributes redirectAttributes)
//    {
//        Userportal user = userService.loadUserByUsername(auth.getName());
//        MovieDto movie = movieService.getMovieById(id);
//    }
}
