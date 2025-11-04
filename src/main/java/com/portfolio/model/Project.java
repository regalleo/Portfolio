package com.portfolio.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private String imageUrl;

    private String githubLink;

    private String liveLink;

    private List<String> technologies; // Array of technologies

    private String category; // web, mobile, ai/ml, data

    private LocalDate completedDate;

    private Boolean featured = false;
}
