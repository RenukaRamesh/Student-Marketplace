package com.renuka.marketplace.controller;

import com.renuka.marketplace.model.Message;
import com.renuka.marketplace.model.User;
import com.renuka.marketplace.repository.MessageRepository;
import com.renuka.marketplace.repository.UserRepository;
import com.renuka.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class MessageController {

    @Autowired private MessageRepository messageRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;

    @GetMapping("/messages")
    public String inbox(Model model, Authentication auth) {
        User me = userService.findByEmail(auth.getName());
        List<Message> all = messageRepository.findAllByUser(me);
        Set<User> contacts = new LinkedHashSet<>();
        for (Message m : all) {
            if (!m.getSender().getId().equals(me.getId()))
                contacts.add(m.getSender());
            if (!m.getReceiver().getId().equals(me.getId()))
                contacts.add(m.getReceiver());
        }
        long unread = all.stream()
                .filter(m -> m.getReceiver().getId().equals(me.getId())
                        && !m.isRead()).count();
        model.addAttribute("contacts", contacts);
        model.addAttribute("currentUser", me);
        model.addAttribute("unreadCount", unread);
        return "messages/inbox";
    }

    @GetMapping("/messages/{userId}")
    public String chat(@PathVariable Long userId,
                       Model model, Authentication auth) {
        User me = userService.findByEmail(auth.getName());
        User other = userRepository.findById(userId).orElse(null);
        if (other == null) return "redirect:/messages";
        List<Message> conversation =
                messageRepository.findConversation(me, other);
        // Mark messages as read
        conversation.stream()
                .filter(m -> m.getReceiver().getId().equals(me.getId())
                        && !m.isRead())
                .forEach(m -> { m.setRead(true); messageRepository.save(m); });
        model.addAttribute("messages", conversation);
        model.addAttribute("currentUser", me);
        model.addAttribute("other", other);
        return "messages/chat";
    }

    @PostMapping("/messages/{userId}")
    public String sendMessage(@PathVariable Long userId,
                              @RequestParam String content,
                              Authentication auth) {
        User me = userService.findByEmail(auth.getName());
        User other = userRepository.findById(userId).orElse(null);
        if (other == null || content.trim().isEmpty())
            return "redirect:/messages";
        Message msg = new Message();
        msg.setContent(content.trim());
        msg.setSender(me);
        msg.setReceiver(other);
        msg.setRead(false);
        messageRepository.save(msg);
        return "redirect:/messages/" + userId;
    }

    @GetMapping("/chat/{userId}")
    public String startChat(@PathVariable Long userId) {
        return "redirect:/messages/" + userId;
    }

    // API for navbar badge count
    @GetMapping("/messages/unread-count")
    @org.springframework.web.bind.annotation.ResponseBody
    public long unreadCount(Authentication auth) {
        User me = userService.findByEmail(auth.getName());
        return messageRepository.findAllByUser(me).stream()
                .filter(m -> m.getReceiver().getId().equals(me.getId())
                        && !m.isRead()).count();
    }
}
