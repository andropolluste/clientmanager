package ee.srini.clientmanager.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * This class extends {@link org.springframework.security.core.userdetails.User} class and adds Id field to its data set.
 * This is used to bind values in our application to logged in user.
 *
 * @author Andro Põlluste
 */
@Getter
@Setter
@ToString
public class User extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = -5747699263288605479L;

    private Long id;

    public User(String username, String password, String role) {
        super(username, password, List.of(new SimpleGrantedAuthority(role)));
    }

}
