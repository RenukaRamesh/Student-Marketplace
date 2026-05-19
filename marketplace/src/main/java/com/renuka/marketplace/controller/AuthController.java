package com.renuka.marketplace.controller;

import com.renuka.marketplace.model.User;
import com.renuka.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute User user,
            @RequestParam(name = "role") String role,
            Model model) {

        // Check email already exists
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("user", user);
            model.addAttribute("error",
                    "This email is already registered. Please login.");
            return "register";
        }

        // Set role
        try {
            if (role.equals("CLIENT")) {
                user.setRole(User.Role.CLIENT);
            } else if (role.equals("FREELANCER")) {
                user.setRole(User.Role.FREELANCER);
            } else {
                model.addAttribute("user", user);
                model.addAttribute("error",
                        "Please select a role.");
                return "register";
            }
        } catch (Exception e) {
            model.addAttribute("user", user);
            model.addAttribute("error",
                    "Please select a role.");
            return "register";
        }

        // Save user
        userService.registerUser(user);
        return "redirect:/login?registered";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth) {
        String role = auth.getAuthorities()
                .iterator().next()
                .getAuthority();

        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        } else if (role.equals("ROLE_FREELANCER")) {
            return "redirect:/freelancer/dashboard";
        } else {
            return "redirect:/client/dashboard";
        }
    }
}