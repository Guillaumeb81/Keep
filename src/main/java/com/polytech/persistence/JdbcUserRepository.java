package com.polytech.persistence;

import com.polytech.services.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Guillaume Bardet on 31/05/2018.
 */
public class JdbcUserRepository implements UserRepository{
    private DataSource dataSource ;

    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        String query = "INSERT INTO users  VALUES('"+user.getUsername()+"','"+user.getPassword()+"',"+user.isEnabled()+")";
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.execute(query);
            statement.execute("insert into authorities values('" + user.getUsername() + "','ADMIN')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
