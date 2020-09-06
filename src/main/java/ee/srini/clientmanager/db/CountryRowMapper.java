package ee.srini.clientmanager.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ee.srini.clientmanager.model.Country;

@Component
public class CountryRowMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet rs, int i) throws SQLException {
        Country result = new Country();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        return result;
    }
}
