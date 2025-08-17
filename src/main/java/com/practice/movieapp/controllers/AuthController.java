package com.practice.movieapp.controllers;

import com.practice.movieapp.model.Userportal;
import com.practice.movieapp.repository.UserPortalRepository;
import com.practice.movieapp.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(@RequestParam(value="error", required = false) String error,
                        Model model){
        if(error != null) model.addAttribute("error", "Неверный пароль. Повторите попытку.");
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new Userportal());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") Userportal user, RedirectAttributes redirectAttributes){

        if(userService.checkUser(user.getUsername())){
            redirectAttributes.addFlashAttribute("error", "Пользователь с таким именем уже существует.");
            return "redirect:/register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateddate(LocalDateTime.now());
        userService.registerUser(user);

        redirectAttributes.addFlashAttribute("success", "Регистрация завершена. Входите в свой профиль.");
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/";

    }


}
