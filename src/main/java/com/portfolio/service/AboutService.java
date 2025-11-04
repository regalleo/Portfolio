package com.portfolio.service;

import com.portfolio.model.About;
import com.portfolio.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutService {

    private final AboutRepository aboutRepository;

    @Autowired
    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    public List<About> getAllAbout() {
        return aboutRepository.findAll();
    }

    public Optional<About> getAboutById(String id) {
        return aboutRepository.findById(id);
    }

    public About getPrimaryAbout() {
        List<About> aboutList = aboutRepository.findAll();
        return aboutList.isEmpty() ? null : aboutList.get(0);
    }

    public About createAbout(About about) {
        return aboutRepository.save(about);
    }

    public About updateAbout(String id, About aboutDetails) {
        return aboutRepository.findById(id)
                .map(about -> {
                    about.setName(aboutDetails.getName());
                    about.setTitle(aboutDetails.getTitle());
                    about.setBio(aboutDetails.getBio());
                    about.setEmail(aboutDetails.getEmail());
                    about.setPhone(aboutDetails.getPhone());
                    about.setLocation(aboutDetails.getLocation());
                    about.setProfileImage(aboutDetails.getProfileImage());
                    about.setResumeUrl(aboutDetails.getResumeUrl());
                    about.setGithubUrl(aboutDetails.getGithubUrl());
                    about.setLinkedinUrl(aboutDetails.getLinkedinUrl());
                    about.setTwitterUrl(aboutDetails.getTwitterUrl());
                    return aboutRepository.save(about);
                })
                .orElseThrow(() -> new RuntimeException("About not found with id: " + id));
    }

    public void deleteAbout(String id) {
        aboutRepository.deleteById(id);
    }
}
