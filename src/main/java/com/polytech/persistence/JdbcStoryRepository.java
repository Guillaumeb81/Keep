package com.polytech.persistence;

import com.polytech.services.Story;
import com.polytech.services.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcStoryRepository implements StoryRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcStoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Story story) {

        String queryUsername = "INSERT INTO STORY (USERNAME,CONTENT) VALUES(?,?)";
        jdbcTemplate.update(queryUsername,story.getUsername(),story.getContent());

    }

    public List<Story> fetch(final Principal principal) {

        String query = "SELECT * FROM STORY WHERE USERNAME='"+principal.getName()+"'";

        return jdbcTemplate.query(query,new RowMapper<Story>() {
            @Override
            public Story mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                String username = resultSet.getString("USERNAME");
                String content = resultSet.getString("CONTENT");
                return new Story(content, username);
            }
        });
    }

    @Override
    public void remove(String content, Principal principal) {
        String queryDelete = "DELETE FROM STORY WHERE USERNAME='"+principal.getName()+"' AND CONTENT='"+content+"'";
        jdbcTemplate.update(queryDelete);
    }

    @Override
    public void edit(String newContent, String content, Principal principal) {
        String queryUpdate = "UPDATE STORY SET CONTENT='" + newContent +"'WHERE USERNAME='"+principal.getName()+"' AND CONTENT='"+content+"'";
        jdbcTemplate.update(queryUpdate);
    }

}
