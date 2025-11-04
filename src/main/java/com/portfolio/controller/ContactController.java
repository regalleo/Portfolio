package com.portfolio.controller;

import com.portfolio.model.Contact;
import com.portfolio.service.ContactService;
import com.portfolio.service.EmailService;
import com.portfolio.dto.InterestRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:3002")
public class ContactController {

    private final ContactService contactService;
    private final EmailService emailService;

    @Autowired
    public ContactController(ContactService contactService, EmailService emailService) {
        this.contactService = contactService;
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> submitContact(@Valid @RequestBody Contact contact) {
        try {
            // Save contact to database
            Contact savedContact = contactService.createContact(contact);

            // Send emails asynchronously
            emailService.sendContactEmailAsync(contact);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Thank you! Your message has been received.");
            response.put("status", "success");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Failed to send message. Please try again.");
            response.put("status", "error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ✅ NEW - Interest Endpoint
    @PostMapping("/interest")
    public ResponseEntity<Map<String, Object>> sendInterestEmail(@RequestBody InterestRequest request) {
        try {
            // Validate email
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Email is required");
                return ResponseEntity.badRequest().body(response);
            }

            // Send emails asynchronously
            emailService.sendInterestEmailAsync(request.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Thank you for your interest! Check your email for confirmation.");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to process your request. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/unread")
    public ResponseEntity<List<Contact>> getUnreadContacts() {
        return ResponseEntity.ok(contactService.getUnreadContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id) {
        return contactService.getContactById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Contact> markAsRead(@PathVariable String id) {
        try {
            Contact updated = contactService.markAsRead(id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
