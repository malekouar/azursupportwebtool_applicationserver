package com.example.tutomvcjpa.controllers;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tutomvcjpa.entities.User;
import com.example.tutomvcjpa.repositories.UserRepository;

@Controller
public class UserController {
    
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getHome(Model model) {
    	model.addAttribute("standardDate", new Date());
    	model.addAttribute("localDateTime", LocalDateTime.now());
    	model.addAttribute("localDate", LocalDate.now());
    	model.addAttribute("timestamp", Instant.now());
        return "index";
    }
    
    @GetMapping("/users")
    public String getAll(Model model) {
    	model.addAttribute("users", userRepository.findAll());
    	return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
//    @GetMapping("/hello")
//    public String hello() {
//        return "hello";
//    }
    
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
    
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
    	return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
}
