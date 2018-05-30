package com.polytech.services;

import com.polytech.persistence.StoryRepository;

import java.security.Principal;


public class PublicationService {

    private StoryRepository storyRepository;

    public PublicationService(StoryRepository storyRepository){
        this.storyRepository = storyRepository;
    }
    public void share(Story story) {
        storyRepository.save(story);
    }

    public void delete(String content, Principal principal) {
        storyRepository.remove(content,principal);
    }

    public void edit(String newContent, String content, Principal principal) {
        storyRepository.edit(newContent,content,principal);
    }
}
