package com.practice.movieapp.controllers;

import com.practice.movieapp.model.Favoriteactor;
import com.practice.movieapp.model.Favoritemovie;
import com.practice.movieapp.model.Userportal;
import com.practice.movieapp.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping(value = "user")
    public String user(Authentication auth, Model model){
        Userportal user = userService.loadUserByUsername(auth.getName());
        List<Favoritemovie> favoritemovies = userService.getFavMovies(user.getId());
        model.addAttribute("favoriteMovies", favoritemovies);
        List<Favoriteactor> favoriteactors = userService.getFavActors(user.getId());
        model.addAttribute("favoriteActors", favoriteactors);
        return "userPage";
    }
}
