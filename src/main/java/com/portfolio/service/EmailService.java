package com.portfolio.service;

import com.portfolio.model.Contact;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String fromEmail;
    private final String adminEmail;

    public EmailService(JavaMailSender mailSender,
                        @Value("${app.email.from}") String fromEmail,
                        @Value("${app.email.receive}") String adminEmail) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
        this.adminEmail = adminEmail;
    }

    @PostConstruct
    public void init() {
        System.out.println("📧 Email from: " + fromEmail);
        System.out.println("📧 Admin email: " + adminEmail);
    }

    // ================== Send HTML Email ==================
    @Async
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
            System.out.println("✅ Email sent to " + to);
        } catch (MessagingException e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
        }
    }

    // ================== Quick Contact with Attachment ==================
    @Async
    public void sendContactEmailWithAttachment(Contact contact) {
        String name = contact.getName() != null ? contact.getName() : "there";

        // ------------------- User Email -------------------
        String userHtml = """
            <html>
            <body style="font-family: Arial, sans-serif; text-align:center; background-color:#f9f9f9; padding:20px;">
                <h2 style="color:#1a73e8;">👋 Hi %s!</h2>
                <p>Thanks for reaching out via Quick Contact. 💬</p>
                <p>We've received your message and will get back to you shortly. 🚀</p>
                <p style="font-size:18px; margin:20px 0;">✨ Feel free to explore my work and connect anytime! 🌟</p>
                <a href="mailto:rajsingh170901@gmail.com"
                   style="display:inline-block; padding:12px 25px; margin:15px 0; color:white;
                          background-color:#1a73e8; text-decoration:none; border-radius:8px; font-weight:bold;">
                   Reach Out ✉️
                </a>
                <p>Best regards,<br/><b>Raj Shekhar Singh 🚀</b></p>
            </body>
            </html>
        """.formatted(name);

        sendHtmlEmail(contact.getEmail(), "📬 Message Received!", userHtml);

        // ------------------- Admin Email with Attachment -------------------
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(adminEmail);
            helper.setSubject("📩 New Contact Submission");

            String adminHtml = """
                <html>
                <body style="font-family: Arial, sans-serif; padding:20px; background-color:#fffbea;">
                    <h3 style="color:#1a73e8;">📩 New Contact Message</h3>
                    <p><b>Name:</b> %s</p>
                    <p><b>Email:</b> %s</p>
                    <p><b>Subject:</b> %s</p>
                    <p><b>Message:</b> %s</p>
                    <p>💡 Follow up promptly to keep engagement high!</p>
                </body>
                </html>
            """.formatted(contact.getName(), contact.getEmail(), contact.getSubject(), contact.getMessage());

            helper.setText(adminHtml, true);

            // Attach file if uploaded
            if (contact.getFile() != null && contact.getFileName() != null) {
                InputStreamSource attachment = new ByteArrayResource(contact.getFile());
                helper.addAttachment(contact.getFileName(), attachment);
            }

            mailSender.send(message);
            System.out.println("✅ Admin email sent with attachment (if any)");
        } catch (MessagingException e) {
            System.err.println("❌ Failed to send admin email: " + e.getMessage());
        }
    }

    // ================== Interest Emails ==================
    @Async
    public void sendInterestEmailAsync(String userEmail) {
        String name = extractNameFromEmail(userEmail);

        String userHtml = """
            <html>
            <body style="font-family: Arial, sans-serif; text-align:center; background-color:#f0f8ff; padding:20px;">
                <h2 style="color:#1a73e8;">👍 Hi %s!</h2>
                <p>Thank you for liking my portfolio website! 💻✨</p>
                <p>You can reach out anytime for queries or collaboration. 📧</p>
                <a href="mailto:rajsingh170901@gmail.com"
                   style="display:inline-block; padding:12px 25px; margin:15px 0; color:white; background-color:#1a73e8; text-decoration:none; border-radius:8px; font-weight:bold;">
                   Reach Out ✉️
                </a>
                <p>Cheers,<br/><b>Raj Shekhar Singh 🚀</b></p>
            </body>
            </html>
        """.formatted(name);

        String adminHtml = """
            <html>
            <body style="font-family: Arial, sans-serif; padding:15px;">
                <h3 style="color:#1a73e8;">📩 New Interest in Portfolio</h3>
                <p>A visitor showed interest in your portfolio website. 🌟</p>
                <p><b>Visitor Email:</b> %s</p>
                <p>💡 Follow up promptly to keep engagement high!</p>
            </body>
            </html>
        """.formatted(userEmail);

        sendHtmlEmail(userEmail, "🎉 Thanks for Liking My Portfolio! 🌟", userHtml);
        sendHtmlEmail(adminEmail, "📩 New Interest in Portfolio", adminHtml);
    }

    // ================== Helper: Extract Name ==================
    private String extractNameFromEmail(String email) {
        if (email == null || !email.contains("@")) return "there";
        String namePart = email.split("@")[0].replaceAll("[^A-Za-z]", " ");
        String[] words = namePart.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) continue;
            sb.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase()).append(" ");
        }
        return sb.toString().trim();
    }
}
