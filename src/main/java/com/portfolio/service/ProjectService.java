package com.portfolio.service;

import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(String id) {
        return projectRepository.findById(id);
    }

    public List<Project> getProjectsByCategory(String category) {
        return projectRepository.findByCategory(category);
    }

    public List<Project> getFeaturedProjects() {
        return projectRepository.findByFeaturedTrue();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(String id, Project projectDetails) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setTitle(projectDetails.getTitle());
                    project.setDescription(projectDetails.getDescription());
                    project.setImageUrl(projectDetails.getImageUrl());
                    project.setGithubLink(projectDetails.getGithubLink());
                    project.setLiveLink(projectDetails.getLiveLink());
                    project.setTechnologies(projectDetails.getTechnologies());
                    project.setCategory(projectDetails.getCategory());
                    project.setCompletedDate(projectDetails.getCompletedDate());
                    project.setFeatured(projectDetails.getFeatured());
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }
}
