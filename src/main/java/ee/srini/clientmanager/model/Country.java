package ee.srini.clientmanager.model;

import java.io.Serializable;

import lombok.Data;

/**
 * Country data class
 *
 * @author Andro Põlluste
 */
@Data
public class Country implements Serializable {

    private static final long serialVersionUID = -5268516525258648117L;

    private Long id;
    private String code;
    private String name;
}
