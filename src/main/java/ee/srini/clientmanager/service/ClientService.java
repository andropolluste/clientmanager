package ee.srini.clientmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ee.srini.clientmanager.db.ClientDao;
import ee.srini.clientmanager.model.Client;

/**
 * Service providing data management logic for {@link Client}
 *
 * @author Andro Põlluste
 */
@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    public Optional<Client> findById(long clientId, long userId) {
        return clientDao.findByIdAndUser(clientId, userId);
    }

    public List<Client> findByUser(long userId) {
        return clientDao.findByUser(userId);
    }

    public Client save(Client client) {
        if (client.isNew()) {
            return insert(client);
        }
        update(client);
        return client;
    }

    public Client insert(Client client) {
        return clientDao.insert(client);
    }

    /**
     * Updates clients changeable fields.
     *
     * @param client
     */
    public void update(Client client) {
        Client existingClient = clientDao.findByIdAndUser(client.getId(), client.getUserId())
                .orElseThrow(() -> new RuntimeException("Updated client not found"));
        existingClient.setFirstName(client.getFirstName());
        existingClient.setLastName(client.getLastName());
        existingClient.setUsername(client.getUsername());
        existingClient.setEmail(client.getEmail());
        existingClient.setAddress(client.getAddress());
        existingClient.setCountryId(client.getCountryId());
        clientDao.update(existingClient);
    }
}
