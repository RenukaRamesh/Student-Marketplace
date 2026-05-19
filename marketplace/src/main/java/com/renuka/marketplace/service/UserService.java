package com.renuka.marketplace.service;

import com.renuka.marketplace.model.User;
import com.renuka.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllFreelancers() {
        return userRepository.findByRole(User.Role.FREELANCER);
    }

    public List<User> getAllClients() {
        return userRepository.findByRole(User.Role.CLIENT);
    }
}