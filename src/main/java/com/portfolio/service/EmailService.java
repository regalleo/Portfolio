package com.portfolio.service;

import com.portfolio.model.Contact;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private String fromEmail;
    private String adminEmail;

    @PostConstruct
    public void init() {
        this.fromEmail = System.getenv("MAIL_USERNAME");
        this.adminEmail = System.getenv("MAIL_RECEIVE");
        System.out.println("📧 Gmail SMTP Config Loaded: " + fromEmail);
    }

    @Async
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);
            System.out.println("✅ Email sent successfully to " + to);
        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Async
    public void sendContactEmailAsync(Contact contact) {
        String subject = "📩 New Message from Portfolio: " + contact.getName();
        String body = "You have received a new message from your portfolio:\n\n" +
                "👤 Name: " + contact.getName() + "\n" +
                "📧 Email: " + contact.getEmail() + "\n\n" +
                "💬 Message:\n" + contact.getMessage() + "\n\n" +
                "🕒 Sent at: " + contact.getCreatedAt();

        sendSimpleMessage(adminEmail, subject, body);
    }

    @Async
    public void sendInterestEmailAsync(String userEmail) {
        String subject = "🎉 Thank You for Your Interest!";
        String body = "Hello!\n\nThank you for showing interest in my work. " +
                "I’ll get back to you soon.\n\nBest regards,\nRaj Shekhar Singh";

        sendSimpleMessage(userEmail, subject, body);
    }

    @Async
    public void sendTestMail() {
        sendSimpleMessage(
                adminEmail,
                "✅ Gmail SMTP Test",
                "This is a test email sent from your Spring Boot app using Gmail SMTP."
        );
    }
}
