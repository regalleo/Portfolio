package com.portfolio.service;

import com.portfolio.model.Contact;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${resend.api-key}")
    private String resendApiKey;

    @Value("${resend.from-email}")
    private String fromEmail;

    private static final String REPLY_TO_EMAIL = "rajsingh170901@gmail.com";
    private static final String ADMIN_EMAIL = "rajsingh170901@gmail.com";

    @PostConstruct
    public void init() {
        System.out.println("📧 Email from: " + fromEmail);
        System.out.println("📧 Reply-to: " + REPLY_TO_EMAIL);
        System.out.println("📧 Admin email: " + ADMIN_EMAIL);
    }

    // ================== Send HTML Email ==================
    @Async
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            Resend resend = new Resend(resendApiKey);

            SendEmailRequest request = SendEmailRequest.builder()
                    .from(fromEmail)
                    .replyTo(REPLY_TO_EMAIL)  // ✅ Replies go to your Gmail
                    .to(to)
                    .subject(subject)
                    .html(htmlContent)
                    .build();

            resend.emails().send(request);
            System.out.println("✅ Email sent to " + to);
        } catch (ResendException e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            e.printStackTrace();
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

        // ------------------- Admin Email -------------------
        String adminHtml = """
            <html>
            <body style="font-family: Arial, sans-serif; padding:20px; background-color:#fffbea;">
                <h3 style="color:#1a73e8;">📩 New Contact Message</h3>
                <p><b>Name:</b> %s</p>
                <p><b>Email:</b> %s</p>
                <p><b>Subject:</b> %s</p>
                <p><b>Message:</b> %s</p>
                <p>💡 Reply directly to this email to respond!</p>
            </body>
            </html>
        """.formatted(contact.getName(), contact.getEmail(), contact.getSubject(), contact.getMessage());

        sendHtmlEmail(ADMIN_EMAIL, "📩 New Contact from " + contact.getName(), adminHtml);

        System.out.println("✅ Admin email sent");
    }

    // ================== Interest Emails ==================
    @Async
    public void sendInterestEmailAsync(String userEmail) {
        String name = extractNameFromEmail(userEmail);

        String userHtml = """
            <html>
            <body style="font-family: Arial, sans-serif; text-align:center; background-color:#f0f8ff; padding:20px;">
                <h2 style="color:#1a73e8;">👍 Hi %s!</h2>
                <p>Thank you for showing interest in my portfolio! 💻✨</p>
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
            <body style="font-family: Arial, sans-serif; padding:15px; background-color:#f0f8ff;">
                <h3 style="color:#1a73e8;">💡 New Interest in Portfolio</h3>
                <p>A visitor showed interest in your portfolio website! 🌟</p>
                <p><b>Visitor Email:</b> %s</p>
                <p>💡 Reach out to build connections!</p>
            </body>
            </html>
        """.formatted(userEmail);

        sendHtmlEmail(userEmail, "🎉 Thanks for Your Interest! 🌟", userHtml);
        sendHtmlEmail(ADMIN_EMAIL, "💡 New Interest from " + name, adminHtml);
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
