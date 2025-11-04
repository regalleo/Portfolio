package com.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    private String id;

    private String name;

    private String category; // Frontend, Backend, Database, Tools

    private Integer proficiency; // 0-100

    private String iconUrl;

    private Integer yearsOfExperience;
}
