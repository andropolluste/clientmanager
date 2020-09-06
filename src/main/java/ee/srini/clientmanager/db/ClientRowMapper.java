package ee.srini.clientmanager.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ee.srini.clientmanager.model.Client;

/**
 * Class providing database query result mapping to {@code Client}.
 *
 * @author Andro Põlluste
 */
@Component
public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int i) throws SQLException {
        Client result = new Client();
        result.setId(rs.getLong("id"));
        result.setUserId(rs.getLong("user_id"));
        result.setFirstName(rs.getString("first_name"));
        result.setLastName(rs.getString("last_name"));
        result.setUsername(rs.getString("username"));
        result.setAddress(rs.getString("address"));
        result.setEmail(rs.getString("email"));
        result.setCountryId(rs.getLong("country_id"));
        return result;
    }
}
