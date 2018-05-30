package com.polytech.config;

import com.polytech.persistence.JdbcStoryRepository;
import com.polytech.persistence.JdbcUserRepository;
import com.polytech.persistence.StoryRepository;
import com.polytech.persistence.UserRepository;
import com.polytech.services.FeedService;
import com.polytech.services.PublicationService;
import com.polytech.services.UserService;
import com.polytech.web.FeedController;
import com.polytech.web.RegisterController;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AppConfig {


    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public StoryRepository storyRepository(JdbcTemplate jdbcTemplate) throws SQLException {
        return new JdbcStoryRepository(jdbcTemplate);
    }

    @Bean
    public FeedService feedService(StoryRepository storyRepository){
        return new FeedService(storyRepository);
    }

    @Bean
    public PublicationService publicationService(StoryRepository storyRepository){
        return new PublicationService(storyRepository);
    }

    @Bean
    public FeedController feedController(FeedService feedService, PublicationService publicationService){
        return new FeedController(feedService,publicationService);
    }

    @Bean
    public RegisterController registerController(UserService userService){
        return new RegisterController(userService);
    }

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .build();
        /*String url = "jdbc:mysql://localhost:3306/polytech";
        String username="root";
        String password="root";

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;*/
    }
    @Bean
    public UserRepository userRepository(DataSource dataSource){
        return new JdbcUserRepository(dataSource);
    }

    @Bean
    public UserService userService (UserRepository userRep, PasswordEncoder passwordEncoder){
        return new UserService(userRep,passwordEncoder);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
