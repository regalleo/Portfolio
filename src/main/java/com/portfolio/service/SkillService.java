package com.portfolio.service;

import com.portfolio.model.Skill;
import com.portfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(String id) {
        return skillRepository.findById(id);
    }

    public List<Skill> getSkillsByCategory(String category) {
        return skillRepository.findByCategory(category);
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(String id, Skill skillDetails) {
        return skillRepository.findById(id)
                .map(skill -> {
                    skill.setName(skillDetails.getName());
                    skill.setCategory(skillDetails.getCategory());
                    skill.setProficiency(skillDetails.getProficiency());
                    skill.setIconUrl(skillDetails.getIconUrl());
                    skill.setYearsOfExperience(skillDetails.getYearsOfExperience());
                    return skillRepository.save(skill);
                })
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
    }

    public void deleteSkill(String id) {
        skillRepository.deleteById(id);
    }
}
