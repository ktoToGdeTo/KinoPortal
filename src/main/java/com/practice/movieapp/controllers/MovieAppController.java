package com.practice.movieapp.controllers;

import com.practice.movieapp.dto.ActorDto;
import com.practice.movieapp.dto.MovieDto;
import com.practice.movieapp.model.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        boolean check = false;
        Userportal user = getCurrentUser();
        if (user != null) {
            check = userService.isFavoriteMovie(user.getId(), id);
        }

        model.addAttribute("isFavorite", check);

        MovieDto movieDto = movieService.getMovieDtoById(id);
        model.addAttribute("movie", movieDto);
        String description = movieDto.getDescription().replace("\\n", "<br><br>");
        model.addAttribute("description", description);

        List<Actorlist> roles = actorListService.getActorsByMovieId(id);
        model.addAttribute("roles", roles);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("dateRelease", sdf.format(movieDto.getDateRelease()));
        return "moviePage";
    }

    @GetMapping(value = "/actors/{id}")
    public String actorById(@PathVariable(name="id") Long id, Model model){
        ActorDto actor = actorService.getActorDtoById(id);
        model.addAttribute("actor", actor);

        List<Actorlist> roles = actorListService.getMoviesByActorId(id);
        model.addAttribute("roles", roles);
        boolean check = false;
        Userportal user = getCurrentUser();
        if (user != null) {
            check = userService.isFavoriteActor(user.getId(), id);
        }

        model.addAttribute("isFavorite", check);

        String description = actor.getDescription().replace("\\n", "<br><br>");
        model.addAttribute("description", description);


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("birthDate", sdf.format(actor.getBirthdate()));
        return "actorPage";
    }



    private Userportal getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) return userService.loadUserByUsername(auth.getName());
        else return null;
    }

    @PostMapping(value = "/actors/{id}/toggle")
    public String toggleFavAct(
            @PathVariable(name = "id") Long id,
            Authentication auth,
            RedirectAttributes redirectAttributes)
    {
        Userportal user = getCurrentUser();
        if(user == null) return "redirect:/login";

        if(userService.isFavoriteActor(user.getId(), id)) {
            userService.deleteFavoriteActor(user.getId(), id);
            redirectAttributes.addFlashAttribute("message", "Актер удален из избранного");
        }
        else{
            Favoriteactor favoriteactor = new Favoriteactor();
            favoriteactor.setUserportal(user);
            favoriteactor.setActor(actorService.getActorById(id));
            FavoriteactorId favActorId = new FavoriteactorId();
            favActorId.setActorId(actorService.getActorById(id).getActorId());
            favActorId.setUserId(user.getId());
            favoriteactor.setId(favActorId);
            userService.addFavoriteActor(favoriteactor);
            redirectAttributes.addFlashAttribute("message", "Актер был добавлен в избранное");
        }

        return "redirect:/actors/" + id;
    }


    @PostMapping(value = "/movies/{id}/toggle")
    public String toggleFavMov(
            @PathVariable(name = "id") Long id)
    {
        Userportal user = getCurrentUser();
        if(user == null) return "redirect:/login";

        if(userService.isFavoriteMovie(user.getId(), id)) {
            userService.deleteFavoriteMovie(user.getId(), id);
        }
        else{
            Favoritemovie favoritemovie = new Favoritemovie();
            favoritemovie.setUserportal(user);
            favoritemovie.setMovie(movieService.getMovieById(id));
            FavoritemovieId favMovId = new FavoritemovieId();
            favMovId.setMovieId(movieService.getMovieById(id).getMovieId());
            favMovId.setUserId(user.getId());
            favoritemovie.setId(favMovId);
            userService.addFavoriteMovie(favoritemovie);
        }

        return "redirect:/movies/" + id;
    }
}
