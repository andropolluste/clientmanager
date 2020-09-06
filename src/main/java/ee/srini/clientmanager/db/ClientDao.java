package ee.srini.clientmanager.db;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import ee.srini.clientmanager.model.Client;

/**
 * This class provides data access functionality for CLIENT table.
 *
 * @author Andro Põlluste
 */

@Repository
public class ClientDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private ClientRowMapper clientRowMapper;

    /**
     * Find client with given id for given user. User id is provided assure that fetched client
     * belongs to user.
     *
     * @param clientId Client.id
     * @param userId Client.userId
     * @return {@code Optional<Client>} or {@code Optional.empty()}
     */
    public Optional<Client> findByIdAndUser(long clientId, long userId) {
        String sql = ""
                + "SELECT * "
                + "FROM client "
                + "WHERE id = :clientId "
                + "    AND user_id = :userId";
        Map<String, Long> params = Map.of(
                "clientId", clientId,
                "userId", userId
        );
        return Optional.ofNullable(DataAccessUtils.singleResult(namedParameterJdbcTemplate.query(sql, params, clientRowMapper)));
    }

    /**
     * Find clients for given user.
     *
     * @param userId User.id
     * @return List of clients or empty list
     */
    public List<Client> findByUser(long userId) {
        String sql = ""
                + "SELECT * "
                + "FROM client "
                + "WHERE user_id = ?";
        return jdbcTemplate.query(sql, clientRowMapper, userId);
    }

    /**
     * Insert client and return client with generated id.
     *
     * @param client Client to store
     * @return inserted Client
     */
    public Client insert(Client client) {
        String sql = ""
                + "INSERT INTO client(user_id, first_name, last_name, username, email, address, country_id) "
                + "VALUES (:userId, :firstName, :lastName, :username, :email, :address, :countryId) ";
        BeanPropertySqlParameterSource  params = new BeanPropertySqlParameterSource(client);
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, generatedKeyHolder);
        client.setId(generatedKeyHolder.getKey().longValue());
        return client;
    }

    /**
     * Update client changeable fields:
     * <ul>
     *     <li>firstName</li>
     *     <li>lastName</li>
     *     <li>username</li>
     *     <li>email</li>
     *     <li>address</li>
     *     <li>countryId</li>
     * </ul>
     * @param client Client with updated values.
     */
    public void update(Client client) {
        String sql = ""
                + "UPDATE client "
                + "SET first_name = :firstName "
                + "      , last_name = :lastName"
                + "      , username = :username"
                + "      , email = :email"
                + "      , address = :address"
                + "      , country_id = :countryId "
                + "WHERE id = :id ";
        BeanPropertySqlParameterSource  params = new BeanPropertySqlParameterSource(client);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
