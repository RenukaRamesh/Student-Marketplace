package com.renuka.marketplace.service;

import com.renuka.marketplace.model.Bid;
import com.renuka.marketplace.model.Gig;
import com.renuka.marketplace.model.User;
import com.renuka.marketplace.repository.BidRepository;
import com.renuka.marketplace.repository.GigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GigService {

    @Autowired private GigRepository gigRepository;
    @Autowired private BidRepository bidRepository;
    @Autowired private EmailService emailService;

    public void postGig(Gig gig, User client) {
        gig.setPostedBy(client);
        gig.setStatus(Gig.Status.OPEN);
        gigRepository.save(gig);
    }

    public List<Gig> getAllOpenGigs() {
        return gigRepository.findByStatus(Gig.Status.OPEN);
    }

    public List<Gig> getGigsByStudent(User client) {
        return gigRepository.findByPostedBy(client);
    }

    public Gig getGigById(Long id) {
        return gigRepository.findById(id).orElse(null);
    }

    public Bid getBidById(Long id) {
        return bidRepository.findById(id).orElse(null);
    }

    public void submitBid(Bid bid, Gig gig, User freelancer) {
        bid.setId(null);
        bid.setGig(gig);
        bid.setBidder(freelancer);
        bid.setStatus(Bid.Status.PENDING);
        bidRepository.save(bid);
        emailService.sendBidReceivedEmail(
                gig.getPostedBy().getEmail(),
                gig.getPostedBy().getName(),
                gig.getTitle(),
                freelancer.getName()
        );
    }

    public List<Bid> getBidsByGig(Gig gig) {
        return bidRepository.findByGig(gig);
    }

    public List<Bid> getBidsByFreelancer(User freelancer) {
        return bidRepository.findByBidder(freelancer);
    }

    public void acceptBid(Long bidId) {
        Bid bid = bidRepository.findById(bidId).orElse(null);
        if (bid != null) {
            bid.setStatus(Bid.Status.ACCEPTED);
            bidRepository.save(bid);
            Gig gig = bid.getGig();
            gig.setStatus(Gig.Status.IN_PROGRESS);
            gigRepository.save(gig);
            emailService.sendBidAcceptedEmail(
                    bid.getBidder().getEmail(),
                    bid.getBidder().getName(),
                    gig.getTitle(),
                    bid.getAmount()
            );
        }
    }

    public void markCompleted(Long gigId) {
        Gig gig = gigRepository.findById(gigId).orElse(null);
        if (gig != null) {
            gig.setStatus(Gig.Status.COMPLETED);
            gigRepository.save(gig);
        }
    }

    public void updateGig(Gig gig) {
        gigRepository.save(gig);
    }

    public boolean deleteGig(Long gigId) {
        Gig gig = gigRepository.findById(gigId).orElse(null);
        if (gig == null) return false;
        List<Bid> bids = bidRepository.findByGig(gig);
        if (!bids.isEmpty()) return false;
        gigRepository.delete(gig);
        return true;
    }
}