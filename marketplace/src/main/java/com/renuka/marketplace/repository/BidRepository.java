package com.renuka.marketplace.repository;

import com.renuka.marketplace.model.Bid;
import com.renuka.marketplace.model.Gig;
import com.renuka.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByGig(Gig gig);
    List<Bid> findByBidder(User bidder);
}
