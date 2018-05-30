package com.polytech.services;

import com.polytech.persistence.StoryRepository;

import java.security.Principal;
import java.util.List;

public class FeedService {

    private StoryRepository storyRepository;


    public FeedService(StoryRepository storyRepository){
        this.storyRepository = storyRepository;
    }
    public List<Story> fetchAllFeeds(Principal principal) {
        return storyRepository.fetch(principal);
    }
}
