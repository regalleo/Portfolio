package com.portfolio.service;

import com.portfolio.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.email.receive}")
    private String receiveEmail;

    // ===== CONTACT FORM EMAILS =====
    @Async
    public void sendContactEmailAsync(Contact contact) {
        try {
            sendEmailToAdmin(contact);
            sendConfirmationToUser(contact);
            logger.info("✅ Contact emails sent successfully");
        } catch (Exception e) {
            logger.error("❌ Error sending contact emails: " + e.getMessage(), e);
        }
    }

    private void sendEmailToAdmin(Contact contact) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(receiveEmail);
        helper.setFrom(contact.getEmail());
        helper.setReplyTo(contact.getEmail());
        helper.setSubject("📨 New Portfolio Contact: " + contact.getSubject());

        String htmlContent = String.format(
                "<html><body style='font-family: Arial;'>" +
                        "<h2 style='color: #667eea;'>🎉 New Contact Message</h2>" +
                        "<p><strong>Name:</strong> %s</p>" +
                        "<p><strong>Email:</strong> <a href='mailto:%s'>%s</a></p>" +
                        "<p><strong>Subject:</strong> %s</p>" +
                        "<div style='background: #f0f4ff; padding: 20px; border-radius: 5px; margin: 20px 0;'>" +
                        "<strong>Message:</strong><br><br>%s</div>" +
                        "<p><a href='mailto:%s?subject=Re: %s' style='background: #667eea; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>Reply</a></p>" +
                        "</body></html>",
                contact.getName(),
                contact.getEmail(), contact.getEmail(),
                contact.getSubject(),
                contact.getMessage().replace("\n", "<br>"),
                contact.getEmail(), contact.getSubject()
        );

        helper.setText(htmlContent, true);
        mailSender.send(message);
        logger.info("✅ Admin email sent to: " + receiveEmail);
    }

    private void sendConfirmationToUser(Contact contact) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(contact.getEmail());
        helper.setFrom("noreply@rajshekhar.dev");
        helper.setSubject("✅ Message Received - Raj Shekhar");

        String htmlContent = String.format(
                "<html><body style='font-family: Arial;'>" +
                        "<h2 style='color: #667eea;'>👋 Hi %s!</h2>" +
                        "<p>Thank you for reaching out! I've received your message and will get back to you soon.</p>" +
                        "<div style='background: #f0f4ff; padding: 15px; border-radius: 5px; margin: 20px 0;'>" +
                        "<strong>Your Subject:</strong> %s</div>" +
                        "<p>I typically respond within 24-48 hours.</p>" +
                        "<p style='margin-top: 30px;'>Best regards,<br><strong>Raj Shekhar Singh</strong><br>" +
                        "Software Developer & Data Engineer<br>" +
                        "📧 rajsingh170901@gmail.com<br>" +
                        "📱 +91-8840082361</p>" +
                        "</body></html>",
                contact.getName(),
                contact.getSubject()
        );

        helper.setText(htmlContent, true);
        mailSender.send(message);
        logger.info("✅ Confirmation email sent to: " + contact.getEmail());
    }

    // ===== INTEREST EMAIL METHODS =====
    @Async
    public void sendInterestEmailAsync(String userEmail) {
        try {
            sendInterestNotificationToAdmin(userEmail);
            sendInterestConfirmationToUser(userEmail);
            logger.info("✅ Interest emails sent successfully");
        } catch (Exception e) {
            logger.error("❌ Error sending interest emails: " + e.getMessage(), e);
        }
    }

    private void sendInterestNotificationToAdmin(String userEmail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(receiveEmail);
        helper.setFrom("noreply@rajshekhar.dev");
        helper.setSubject("👍 New Interest Notification");

        String htmlContent = String.format(
                "<html><body style='font-family: Arial;'>" +
                        "<h2 style='color: #667eea;'>👍 New Portfolio Interest</h2>" +
                        "<p><strong>User Email:</strong> <a href='mailto:%s'>%s</a></p>" +
                        "<p>A user has shown interest in your portfolio by clicking the 👍 button.</p>" +
                        "<p><strong>Time:</strong> %s</p>" +
                        "<p><a href='mailto:%s' style='background: #667eea; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>Reply to User</a></p>" +
                        "</body></html>",
                userEmail, userEmail,
                java.time.LocalDateTime.now(),
                userEmail
        );

        helper.setText(htmlContent, true);
        mailSender.send(message);
        logger.info("✅ Interest notification sent to admin");
    }

    private void sendInterestConfirmationToUser(String userEmail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(userEmail);
        helper.setFrom("noreply@rajshekhar.dev");
        helper.setSubject("Thank You for Showing Interest! 👍");

        String htmlContent =
                "<html><body style='font-family: Arial;'>" +
                        "<h2 style='color: #667eea;'>👋 Thank You!</h2>" +
                        "<p>Hi,</p>" +
                        "<p>Thank you for showing interest in my portfolio. I appreciate your support! 🙌</p>" +
                        "<p>I'll get back to you soon with more exciting projects and updates.</p>" +
                        "<p style='margin-top: 30px;'>Best regards,<br><strong>Raj Shekhar Singh</strong><br>" +
                        "Full-Stack Developer & Data Engineer<br>" +
                        "📧 rajsingh170901@gmail.com<br>" +
                        "📱 +91-8840082361</p>" +
                        "</body></html>";

        helper.setText(htmlContent, true);
        mailSender.send(message);
        logger.info("✅ Interest confirmation sent to: " + userEmail);
    }
}
