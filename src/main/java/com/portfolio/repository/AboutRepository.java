package com.portfolio.repository;

import com.portfolio.model.About;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends MongoRepository<About, String> {
}
