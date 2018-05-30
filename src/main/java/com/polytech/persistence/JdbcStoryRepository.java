package com.polytech.persistence;

import com.polytech.services.Story;
import com.polytech.services.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcStoryRepository implements StoryRepository {

    private JdbcTemplate jdbcTemplate;


    //

    public JdbcStoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Story story) {
        String query = "INSERT INTO STORY (CONTENT)VALUES(?)";
        jdbcTemplate.update(query, story.getContent());
    }

    public List<Story> findAll() {
        String query = "SELECT * FROM STORY";
        return jdbcTemplate.query(query, new StoryMapper());

    }

    class StoryMapper implements RowMapper<Story> {

        @Override
        public Story mapRow(ResultSet rs, int rowNum) throws SQLException {
            String content = rs.getString("CONTENT");
            return new Story(content);
        }
    }

    public void modify(Story story) {
        String query = "UPDATE STORY SET CONTENT = "+ story.getContent() + " WHERE ID=" + story.getId();
        jdbcTemplate.update(query, story.getContent());
    }

    public void supprimme(int id){
        String query = "DELETE FROM STORY WHERE ID = ?";
        jdbcTemplate.update(query, id);
    }


    public void saveUser(User user) {
        String query = "INSERT INTO users (CONTENT)VALUES('"+ user.getUsername() +"','"+ user.getPassword() +"',true)";
        jdbcTemplate.update(query);
    }

    public boolean findUser(User user) {
        String query = "SELECT * FROM Users WHERE username='"+ user.getUsername() +"' AND  password='"+ user.getPassword() +"'";
        return jdbcTemplate.query(query, new StoryMapper()) == null;
    }


}
