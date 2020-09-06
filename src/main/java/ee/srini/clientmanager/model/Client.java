package ee.srini.clientmanager.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Client data class with validation rules.
 *
 * @author Andro Põlluste
 */
@Data
public class Client implements Serializable {

    private static final long serialVersionUID = 8548480928348293766L;

    private Long id;
    private Long userId;

    @NotNull(message = "First name must be set")
    @Size(max = 100, message = "First name must be under 100 characters")
    private String firstName;

    @NotNull(message = "Last name must be set")
    @Size(max = 100, message = "Last name must be under 100 characters")
    private String lastName;

    @NotNull(message = "Username must be set")
    @Size(max = 100, message = "Username must be under 100 characters")
    private String username;

    @Email(message = "Valid email required")
    @Size(max = 100, message = "Email must be under 100 characters")
    private String email;

    @NotNull(message = "Address must be set")
    @Size(max = 100, message = "Address must be under 100 characters")
    private String address;

    @NotNull(message = "Country must be selected")
    private Long countryId;

    public boolean isNew() {
        return id == null;
    }
}
