package com.portfolio.repository;

import com.portfolio.model.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends MongoRepository<Experience, String> {
    List<Experience> findByOrderByStartDateDesc();
}
