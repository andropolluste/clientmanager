package ee.srini.clientmanager.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ee.srini.clientmanager.model.User;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        String username = rs.getString("username");
        String password = rs.getString("password");
        String role = rs.getString("role");
        User result = new User(username, password, role);
        result.setId(rs.getLong("id"));
        return result;
    }
}
