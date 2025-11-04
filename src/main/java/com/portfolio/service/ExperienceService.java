package com.portfolio.service;

import com.portfolio.model.Experience;
import com.portfolio.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public List<Experience> getAllExperiences() {
        return experienceRepository.findByOrderByStartDateDesc();
    }

    public Optional<Experience> getExperienceById(String id) {
        return experienceRepository.findById(id);
    }

    public Experience createExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Experience updateExperience(String id, Experience experienceDetails) {
        return experienceRepository.findById(id)
                .map(experience -> {
                    experience.setCompany(experienceDetails.getCompany());
                    experience.setPosition(experienceDetails.getPosition());
                    experience.setDescription(experienceDetails.getDescription());
                    experience.setStartDate(experienceDetails.getStartDate());
                    experience.setEndDate(experienceDetails.getEndDate());
                    experience.setCurrent(experienceDetails.getCurrent());
                    experience.setLocation(experienceDetails.getLocation());
                    experience.setAchievements(experienceDetails.getAchievements());
                    return experienceRepository.save(experience);
                })
                .orElseThrow(() -> new RuntimeException("Experience not found with id: " + id));
    }

    public void deleteExperience(String id) {
        experienceRepository.deleteById(id);
    }
}
