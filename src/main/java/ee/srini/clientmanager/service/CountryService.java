package ee.srini.clientmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ee.srini.clientmanager.db.CountryDao;
import ee.srini.clientmanager.model.Country;

/**
 * Service providing data management logic for {@link Country}
 *
 * @author Andro Põlluste
 */
@Service
public class CountryService {

    @Autowired
    private CountryDao countryDao;

    public List<Country> findCountries() {
        return countryDao.findAll();
    }
}
