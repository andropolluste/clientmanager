package ee.srini.clientmanager.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ee.srini.clientmanager.model.Country;

/**
 * This class provides data access functionality for COUNTRY table
 *
 * @author Andro Põlluste
 */
@Repository
public class CountryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CountryRowMapper countryRowMapper;

    public List<Country> findAll() {
        String sql = ""
                + "SELECT  *"
                + "FROM country";

        return jdbcTemplate.query(sql, countryRowMapper);
    }
}
