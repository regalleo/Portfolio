package com.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "experiences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

    @Id
    private String id;

    private String company;

    private String position;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean current = false;

    private String location;

    private List<String> achievements; // Array of achievements
}
