package com.polytech.persistence;

import com.polytech.services.Story;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class InMemoryStoryRepository implements StoryRepository {

    private static List<Story> dataBase = new ArrayList();

    public void save(Story content){
        dataBase.add(content);
    }

    public List<Story> fetch(Principal principal){
        return dataBase;
    }

    @Override
    public void remove(String content, Principal principal) {

    }

    @Override
    public void edit(String newContent, String content, Principal principal) {

    }
}
