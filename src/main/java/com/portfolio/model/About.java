package com.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "about")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class About {

    @Id
    private String id;

    private String name;

    private String title;

    private String bio;

    private String email;

    private String phone;

    private String location;

    private String profileImage;

    private String resumeUrl;

    private String githubUrl;

    private String linkedinUrl;

    private String twitterUrl;
}
