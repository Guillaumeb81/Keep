package com.polytech.persistence;

import com.polytech.services.Story;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

public interface StoryRepository {

    List<Story> fetch(Principal principal);

    void save(Story content);

    void remove(String content, Principal principal);

    void edit(String newContent, String content, Principal principal);
}
