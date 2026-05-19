package com.renuka.marketplace.controller;

import com.renuka.marketplace.model.Bid;
import com.renuka.marketplace.model.Gig;
import com.renuka.marketplace.model.User;
import com.renuka.marketplace.repository.BidRepository;
import com.renuka.marketplace.repository.GigRepository;
import com.renuka.marketplace.repository.MessageRepository;
import com.renuka.marketplace.repository.ReviewRepository;
import com.renuka.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired private UserRepository userRepository;
    @Autowired private GigRepository gigRepository;
    @Autowired private BidRepository bidRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private MessageRepository messageRepository;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("totalUsers", userRepository.count());
        model.addAttribute("totalGigs", gigRepository.count());
        model.addAttribute("totalBids", bidRepository.count());

        long clients = userRepository.findByRole(User.Role.CLIENT).size();
        long freelancers = userRepository.findByRole(User.Role.FREELANCER).size();
        model.addAttribute("totalClients", clients);
        model.addAttribute("totalFreelancers", freelancers);

        long openGigs = gigRepository.findByStatus(Gig.Status.OPEN).size();
        long inProgressGigs = gigRepository.findByStatus(Gig.Status.IN_PROGRESS).size();
        long completedGigs = gigRepository.findByStatus(Gig.Status.COMPLETED).size();
        model.addAttribute("openGigs", openGigs);
        model.addAttribute("inProgressGigs", inProgressGigs);
        model.addAttribute("totalCompleted", completedGigs);

        long pendingBids = bidRepository.findAll().stream()
                .filter(b -> b.getStatus() == Bid.Status.PENDING).count();
        long acceptedBids = bidRepository.findAll().stream()
                .filter(b -> b.getStatus() == Bid.Status.ACCEPTED).count();
        model.addAttribute("pendingBids", pendingBids);
        model.addAttribute("acceptedBids", acceptedBids);

        return "admin/dashboard";
    }

    @GetMapping("/admin/users")
    public String allUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    @PostMapping("/admin/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userRepository.findById(id).ifPresent(user -> {
            messageRepository.findAllByUser(user)
                    .forEach(messageRepository::delete);
            reviewRepository.findByReviewee(user)
                    .forEach(reviewRepository::delete);
            bidRepository.findByBidder(user)
                    .forEach(bidRepository::delete);
            gigRepository.findByPostedBy(user).forEach(gig -> {
                bidRepository.findByGig(gig)
                        .forEach(bidRepository::delete);
                gigRepository.delete(gig);
            });
            userRepository.delete(user);
        });
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/gigs")
    public String allGigs(Model model) {
        model.addAttribute("gigs", gigRepository.findAll());
        return "admin/gigs";
    }

    @PostMapping("/admin/gigs/{id}/delete")
    public String deleteGig(@PathVariable Long id) {
        gigRepository.findById(id).ifPresent(gig -> {
            bidRepository.findByGig(gig).forEach(bidRepository::delete);
            gigRepository.delete(gig);
        });
        return "redirect:/admin/gigs";
    }

    @GetMapping("/admin/bids")
    public String allBids(Model model) {
        model.addAttribute("bids", bidRepository.findAll());
        return "admin/bids";
    }

    @PostMapping("/admin/bids/{id}/delete")
    public String deleteBid(@PathVariable Long id) {
        bidRepository.deleteById(id);
        return "redirect:/admin/bids";
    }
}