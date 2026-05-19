package com.renuka.marketplace.controller;

import com.renuka.marketplace.model.Bid;
import com.renuka.marketplace.model.Gig;
import com.renuka.marketplace.model.Review;
import com.renuka.marketplace.model.User;
import com.renuka.marketplace.repository.ReviewRepository;
import com.renuka.marketplace.service.GigService;
import com.renuka.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class GigController {

    @Autowired private GigService gigService;
    @Autowired private UserService userService;
    @Autowired private ReviewRepository reviewRepository;

    @GetMapping("/client/post-gig")
    public String showPostGig(Model model) {
        model.addAttribute("gig", new Gig());
        return "client/post-gig";
    }

    @PostMapping("/client/post-gig")
    public String postGig(@ModelAttribute Gig gig,
                          Authentication auth) {
        User client = userService.findByEmail(auth.getName());
        gigService.postGig(gig, client);
        return "redirect:/client/my-gigs";
    }

    @GetMapping("/client/my-gigs")
    public String myGigs(Model model, Authentication auth) {
        User client = userService.findByEmail(auth.getName());
        model.addAttribute("gigs",
                gigService.getGigsByStudent(client));
        return "client/my-gigs";
    }

    @GetMapping("/client/gig/{id}/bids")
    public String viewBids(@PathVariable Long id,
                           Model model) {
        Gig gig = gigService.getGigById(id);
        model.addAttribute("gig", gig);
        model.addAttribute("bids",
                gigService.getBidsByGig(gig));
        return "client/view-bids";
    }

    @PostMapping("/client/accept-bid/{bidId}")
    public String acceptBid(@PathVariable Long bidId) {
        gigService.acceptBid(bidId);
        return "redirect:/client/my-gigs";
    }

    @PostMapping("/client/gig/{id}/complete")
    public String markComplete(@PathVariable Long id) {
        gigService.markCompleted(id);
        return "redirect:/client/my-gigs";
    }

    @GetMapping("/client/gig/{id}/edit")
    public String showEditGig(@PathVariable Long id,
                              Model model,
                              Authentication auth) {
        Gig gig = gigService.getGigById(id);
        User client = userService.findByEmail(auth.getName());
        if (!gig.getPostedBy().getId().equals(client.getId()))
            return "redirect:/client/my-gigs";
        if (!gigService.getBidsByGig(gig).isEmpty())
            return "redirect:/client/my-gigs?cannotEdit=true";
        model.addAttribute("gig", gig);
        return "client/edit-gig";
    }

    @PostMapping("/client/gig/{id}/edit")
    public String saveEditGig(@PathVariable Long id,
                              @ModelAttribute Gig updatedGig,
                              Authentication auth) {
        Gig existing = gigService.getGigById(id);
        User client  = userService.findByEmail(auth.getName());
        if (!existing.getPostedBy().getId()
                .equals(client.getId()))
            return "redirect:/client/my-gigs";
        existing.setTitle(updatedGig.getTitle());
        existing.setDescription(updatedGig.getDescription());
        existing.setBudget(updatedGig.getBudget());
        existing.setCategory(updatedGig.getCategory());
        existing.setDeadlineDate(updatedGig.getDeadlineDate());
        gigService.updateGig(existing);
        return "redirect:/client/my-gigs";
    }

    @PostMapping("/client/gig/{id}/delete")
    public String deleteGig(@PathVariable Long id,
                            Authentication auth) {
        Gig gig = gigService.getGigById(id);
        User client = userService.findByEmail(auth.getName());
        if (!gig.getPostedBy().getId().equals(client.getId()))
            return "redirect:/client/my-gigs";
        boolean deleted = gigService.deleteGig(id);
        if (!deleted)
            return "redirect:/client/my-gigs?cannotDelete=true";
        return "redirect:/client/my-gigs";
    }

    @GetMapping("/client/freelancers")
    public String browseFreelancers(Model model) {
        model.addAttribute("freelancers",
                userService.getAllFreelancers());
        return "client/freelancers";
    }

    @GetMapping("/client/contract/{bidId}")
    public String viewContract(@PathVariable Long bidId,
                               Model model) {
        Bid bid = gigService.getBidById(bidId);
        if (bid == null) return "redirect:/client/my-gigs";
        model.addAttribute("bid", bid);
        return "client/contract";
    }

    @GetMapping("/client/timeline")
    public String timeline(Model model, Authentication auth) {
        User client = userService.findByEmail(auth.getName());
        model.addAttribute("gigs",
                gigService.getGigsByStudent(client));
        return "client/timeline";
    }

    @GetMapping("/client/calendar")
    public String calendar(Model model, Authentication auth) {
        User client = userService.findByEmail(auth.getName());
        model.addAttribute("gigs",
                gigService.getGigsByStudent(client));
        return "client/calendar";
    }

    @GetMapping("/freelancer/browse")
    public String browseGigs(Model model,
                             Authentication auth) {
        User freelancer = userService.findByEmail(auth.getName());
        model.addAttribute("gigs",
                gigService.getAllOpenGigs());
        model.addAttribute("freelancerSkills",
                freelancer.getSkills() != null
                        ? freelancer.getSkills().toLowerCase() : "");
        return "freelancer/browse";
    }

    @GetMapping("/freelancer/gig/{id}")
    public String viewGig(@PathVariable Long id,
                          Model model) {
        model.addAttribute("gig", gigService.getGigById(id));
        model.addAttribute("bid", new Bid());
        return "freelancer/gig-detail";
    }

    @PostMapping("/freelancer/gig/{id}/bid")
    public String submitBid(@PathVariable Long id,
                            @ModelAttribute Bid bid,
                            Authentication auth) {
        User freelancer = userService.findByEmail(auth.getName());
        Gig gig = gigService.getGigById(id);
        gigService.submitBid(bid, gig, freelancer);
        return "redirect:/freelancer/my-bids";
    }

    @GetMapping("/freelancer/my-bids")
    public String myBids(Model model, Authentication auth) {
        User freelancer = userService.findByEmail(auth.getName());
        model.addAttribute("bids",
                gigService.getBidsByFreelancer(freelancer));
        return "freelancer/my-bids";
    }

    @GetMapping("/freelancer/earnings")
    public String earnings(Model model, Authentication auth) {
        User freelancer = userService.findByEmail(auth.getName());
        List<Bid> bids  = gigService.getBidsByFreelancer(freelancer);

        long totalBids    = bids.size();
        long acceptedBids = bids.stream()
                .filter(b -> b.getStatus() == Bid.Status.ACCEPTED)
                .count();

        // Money RECEIVED = projects that are COMPLETED
        double totalEarned = bids.stream()
                .filter(b -> b.getStatus() == Bid.Status.ACCEPTED
                        && b.getGig().getStatus() == Gig.Status.COMPLETED)
                .mapToDouble(b -> b.getAmount() != null
                        ? b.getAmount() : 0)
                .sum();

        // Money PENDING = accepted but IN_PROGRESS (not yet done)
        double pendingEarnings = bids.stream()
                .filter(b -> b.getStatus() == Bid.Status.ACCEPTED
                        && b.getGig().getStatus() == Gig.Status.IN_PROGRESS)
                .mapToDouble(b -> b.getAmount() != null
                        ? b.getAmount() : 0)
                .sum();

        double rate = totalBids > 0
                ? (acceptedBids * 100.0 / totalBids) : 0;

        model.addAttribute("bids",            bids);
        model.addAttribute("totalBids",       totalBids);
        model.addAttribute("acceptedBids",    acceptedBids);
        model.addAttribute("totalEarned",     totalEarned);
        model.addAttribute("pendingEarnings", pendingEarnings);
        model.addAttribute("acceptanceRate",
                String.format("%.1f", rate));
        return "freelancer/earnings";
    }
    @GetMapping("/freelancer/timeline")
    public String freelancerTimeline(Model model,
                                     Authentication auth) {
        User freelancer = userService.findByEmail(auth.getName());
        List<Bid> acceptedBids = gigService
                .getBidsByFreelancer(freelancer)
                .stream()
                .filter(b -> b.getStatus() == Bid.Status.ACCEPTED)
                .collect(java.util.stream.Collectors.toList());
        model.addAttribute("acceptedBids", acceptedBids);
        return "freelancer/timeline";
    }

    @GetMapping("/freelancer/calendar")
    public String freelancerCalendar(Model model,
                                     Authentication auth) {
        User freelancer = userService.findByEmail(auth.getName());
        List<Bid> acceptedBids = gigService
                .getBidsByFreelancer(freelancer)
                .stream()
                .filter(b -> b.getStatus() == Bid.Status.ACCEPTED)
                .collect(java.util.stream.Collectors.toList());
        model.addAttribute("acceptedBids", acceptedBids);
        return "freelancer/calendar";
    }

    @GetMapping("/leaderboard")
    public String leaderboard(Model model) {
        List<User> freelancers =
                userService.getAllFreelancers();

        Map<Long, Double>  avgRatings   = new HashMap<>();
        Map<Long, Integer> reviewCounts = new HashMap<>();

        for (User f : freelancers) {
            List<Review> reviews =
                    reviewRepository.findByReviewee(f);
            reviewCounts.put(f.getId(), reviews.size());
            if (!reviews.isEmpty()) {
                double avg = reviews.stream()
                        .mapToInt(Review::getRating)
                        .average()
                        .orElse(0.0);
                avgRatings.put(f.getId(),
                        Math.round(avg * 10.0) / 10.0);
            } else {
                avgRatings.put(f.getId(), 0.0);
            }
        }

        freelancers.sort((a, b) -> {
            int cmp = Double.compare(
                    avgRatings.getOrDefault(b.getId(), 0.0),
                    avgRatings.getOrDefault(a.getId(), 0.0));
            if (cmp != 0) return cmp;
            return Integer.compare(
                    reviewCounts.getOrDefault(b.getId(), 0),
                    reviewCounts.getOrDefault(a.getId(), 0));
        });

        model.addAttribute("freelancers",  freelancers);
        model.addAttribute("avgRatings",   avgRatings);
        model.addAttribute("reviewCounts", reviewCounts);
        return "leaderboard";
    }
}