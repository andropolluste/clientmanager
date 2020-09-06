package ee.srini.clientmanager.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ee.srini.clientmanager.model.User;

/**
 * This class provides data access functionality for USER table.
 *
 * @author Andro Põlluste
 */

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRowMapper userRowMapper;

    public Optional<User> findByUsername(String username) {
        String sql = ""
                + "SELECT * "
                + "FROM user "
                + "WHERE username = ? ";

        return Optional.ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query(sql, userRowMapper, username)));
    }
}
