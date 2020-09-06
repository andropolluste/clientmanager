package ee.srini.clientmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ee.srini.clientmanager.db.UserDao;
import ee.srini.clientmanager.model.User;

/**
 * Service providing data management logic for {@link User}
 *
 * @author Andro Põlluste
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
