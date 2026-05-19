package com.renuka.marketplace.controller;

import com.renuka.marketplace.model.User;
import com.renuka.marketplace.repository.ReviewRepository;
import com.renuka.marketplace.repository.UserRepository;
import com.renuka.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private ReviewRepository reviewRepository;

    @GetMapping("/profile")
    public String ownProfile(Model model, Authentication auth) {
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("reviews",
                reviewRepository.findByReviewee(user));
        model.addAttribute("isOwn", true);
        return "profile";
    }

    @GetMapping("/profile/{id}")
    public String otherProfile(@PathVariable Long id,
                               Model model, Authentication auth) {
        User user = userRepository.findById(id).orElse(null);
        User current = userService.findByEmail(auth.getName());
        if (user == null) return "redirect:/dashboard";
        model.addAttribute("user", user);
        model.addAttribute("reviews",
                reviewRepository.findByReviewee(user));
        model.addAttribute("isOwn",
                user.getId().equals(current.getId()));
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String showEditProfile(Model model, Authentication auth) {
        model.addAttribute("user",
                userService.findByEmail(auth.getName()));
        return "edit-profile";
    }

    @PostMapping("/profile/edit")
    public String saveProfile(@ModelAttribute User updated,
                              Authentication auth) {
        User existing = userService.findByEmail(auth.getName());
        existing.setName(updated.getName());
        existing.setBio(updated.getBio());
        existing.setSkills(updated.getSkills());
        existing.setTopProjects(updated.getTopProjects());
        existing.setGithubUrl(updated.getGithubUrl());
        userService.updateUser(existing);
        return "redirect:/profile";
    }

    @GetMapping("/settings")
    public String settings(Model model, Authentication auth) {
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("user", user);
        // Pass role string for Thymeleaf checks
        model.addAttribute("isClient",
                user.getRole() == com.renuka.marketplace.model.User.Role.CLIENT);
        model.addAttribute("isFreelancer",
                user.getRole() == com.renuka.marketplace.model.User.Role.FREELANCER);
        model.addAttribute("isAdmin",
                user.getRole() == com.renuka.marketplace.model.User.Role.ADMIN);
        return "settings";
    }
}