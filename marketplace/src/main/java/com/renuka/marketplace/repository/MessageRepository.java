package com.renuka.marketplace.repository;

import com.renuka.marketplace.model.Message;
import com.renuka.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE " +
            "(m.sender = :u1 AND m.receiver = :u2) OR " +
            "(m.sender = :u2 AND m.receiver = :u1) " +
            "ORDER BY m.sentAt ASC")
    List<Message> findConversation(@Param("u1") User u1,
                                   @Param("u2") User u2);

    @Query("SELECT m FROM Message m WHERE " +
            "m.sender = :user OR m.receiver = :user " +
            "ORDER BY m.sentAt DESC")
    List<Message> findAllByUser(@Param("user") User user);
}
