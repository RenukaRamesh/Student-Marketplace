package com.renuka.marketplace.controller;

import com.renuka.marketplace.model.Review;
import com.renuka.marketplace.model.User;
import com.renuka.marketplace.repository.ReviewRepository;
import com.renuka.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    // Show review form
    @GetMapping("/review/{freelancerEmail}")
    public String showReviewForm(@PathVariable String freelancerEmail,
                                 Model model) {
        User freelancer = userService.findByEmail(freelancerEmail);
        model.addAttribute("freelancer", freelancer);
        model.addAttribute("review", new Review());
        return "review-form";
    }

    // Submit review
    @PostMapping("/review/{freelancerEmail}")
    public String submitReview(@PathVariable String freelancerEmail,
                               @ModelAttribute Review review,
                               Authentication auth) {
        User reviewer = userService.findByEmail(auth.getName());
        User reviewee = userService.findByEmail(freelancerEmail);
        review.setId(null);
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        reviewRepository.save(review);
        return "redirect:/client/my-gigs";
    }
}

