package com.portfolio.service;

import com.portfolio.model.Contact;
import com.portfolio.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(Contact contact) {
        try {
            // Set timestamp
            if (contact.getCreatedAt() == null) {
                contact.setCreatedAt(LocalDateTime.now());
            }

            // Save to database
            Contact savedContact = contactRepository.save(contact);
            logger.info("✅ Contact saved: " + contact.getName());

            // Email sending happens asynchronously in EmailService
            // Don't wait for it here - just save and return

            return savedContact;

        } catch (Exception e) {
            logger.error("❌ Error creating contact: " + e.getMessage(), e);
            throw new RuntimeException("Failed to save contact", e);
        }
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public List<Contact> getUnreadContacts() {
        return contactRepository.findByReadFalse();
    }

    public Optional<Contact> getContactById(String id) {
        return contactRepository.findById(id);
    }

    public Contact markAsRead(String id) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setRead(true);
                    return contactRepository.save(contact);
                })
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
    }

    public void deleteContact(String id) {
        contactRepository.deleteById(id);
    }
}
