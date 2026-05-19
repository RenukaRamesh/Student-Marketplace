package com.renuka.marketplace.controller;

import com.renuka.marketplace.model.User;
import com.renuka.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @GetMapping("/client/dashboard")
    public String clientDashboard(Model model, Authentication auth) {
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("user", user);
        return "client/dashboard";
    }

    @GetMapping("/freelancer/dashboard")
    public String freelancerDashboard(Model model, Authentication auth) {
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("user", user);
        return "freelancer/dashboard";
    }
}

