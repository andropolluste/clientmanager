package ee.srini.clientmanager.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Helper class to provide password encoding functionality with encoder user in
 * application
 *
 *  @author Andro Põlluste
 */
public class PasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("potter = " + passwordEncoder.encode("potter"));
        System.out.println("granger = " + passwordEncoder.encode("granger"));
        System.out.println("weasley = " + passwordEncoder.encode("weasley"));
    }
}
