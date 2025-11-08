package com.biblestudy.portal.controller;

import com.biblestudy.portal.model.User;
import com.biblestudy.portal.model.Verse;
import com.biblestudy.portal.repository.UserRepository;
import com.biblestudy.portal.repository.VerseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerseRepository verseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "register_success";
    }


    @GetMapping("/home")
    public String home(Model model, Principal principal) {

        String email = principal.getName();


        User user = userRepository.findByEmail(email);


        model.addAttribute("username", user.getUsername());


        Verse verse = verseRepository.findRandomVerse();
        if (verse != null) {
            model.addAttribute("verse", verse);
        }

        return "home";
    }


    @GetMapping("/logout-success")
    public String logoutPage() {
        return "redirect:/login?logout";
    }
}
