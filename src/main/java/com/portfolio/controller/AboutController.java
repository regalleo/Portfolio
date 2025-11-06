package com.portfolio.controller;

import com.portfolio.model.About;
import com.portfolio.service.AboutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/about")
@CrossOrigin(origins = "https://raj-shekhar-portfolio.netlify.app")
public class AboutController {

    private final AboutService aboutService;

    @Autowired
    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @GetMapping
    public ResponseEntity<List<About>> getAllAbout() {
        return ResponseEntity.ok(aboutService.getAllAbout());
    }

    @GetMapping("/primary")
    public ResponseEntity<About> getPrimaryAbout() {
        About about = aboutService.getPrimaryAbout();
        return about != null ? ResponseEntity.ok(about) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<About> getAboutById(@PathVariable String id) {
        return aboutService.getAboutById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<About> createAbout(@Valid @RequestBody About about) {
        About created = aboutService.createAbout(about);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<About> updateAbout(
            @PathVariable String id,
            @Valid @RequestBody About about) {
        try {
            About updated = aboutService.updateAbout(id, about);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbout(@PathVariable String id) {
        aboutService.deleteAbout(id);
        return ResponseEntity.noContent().build();
    }
}
