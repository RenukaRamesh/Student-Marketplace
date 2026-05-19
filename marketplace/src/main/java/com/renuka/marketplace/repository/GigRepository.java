package com.renuka.marketplace.repository;

import com.renuka.marketplace.model.Gig;
import com.renuka.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GigRepository extends JpaRepository<Gig, Long> {
    List<Gig> findByPostedBy(User user);
    List<Gig> findByStatus(Gig.Status status);
}