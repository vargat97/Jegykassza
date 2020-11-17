package hu.bme.piedpiper.agilis.jegykassza.user.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCreateRequest implements Serializable {

    private String userName;

    private String password;

    private String emailAddress;

}
