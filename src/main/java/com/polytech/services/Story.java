package com.polytech.services;

import java.util.Objects;

public class Story {
    private String content;
    private String username;

    public Story(String content, String username){
        this.content = content;
        this.username = username;
    }

    public String getContent() {
        return this.content;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Story story = (Story) o;
        return Objects.equals(content, story.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
