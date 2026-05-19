package com.renuka.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBidAcceptedEmail(String toEmail,
                                     String freelancerName,
                                     String projectTitle,
                                     double amount) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("🎉 Your Bid Was Accepted! — FreelanceHub");
            message.setText(
                    "Hi " + freelancerName + ",\n\n" +
                            "Great news! Your bid has been accepted.\n\n" +
                            "Project: " + projectTitle + "\n" +
                            "Agreed Amount: ₹" + amount + "\n\n" +
                            "Login to FreelanceHub to view your contract.\n\n" +
                            "Best regards,\nFreelanceHub Team"
            );
            mailSender.send(message);
            System.out.println("Bid accepted email sent to: " + toEmail);
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }
    }

    public void sendBidReceivedEmail(String toEmail,
                                     String clientName,
                                     String projectTitle,
                                     String freelancerName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("📨 New Bid Received — FreelanceHub");
            message.setText(
                    "Hi " + clientName + ",\n\n" +
                            "You received a new bid on your project.\n\n" +
                            "Project: " + projectTitle + "\n" +
                            "Freelancer: " + freelancerName + "\n\n" +
                            "Login to FreelanceHub to review and accept the bid.\n\n" +
                            "Best regards,\nFreelanceHub Team"
            );
            mailSender.send(message);
            System.out.println("Bid received email sent to: " + toEmail);
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }
    }
}
