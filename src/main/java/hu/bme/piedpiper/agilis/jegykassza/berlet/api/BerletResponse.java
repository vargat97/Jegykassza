package hu.bme.piedpiper.agilis.jegykassza.berlet.api;

import hu.bme.piedpiper.agilis.jegykassza.user.api.UserResponse;
import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntity;
import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class BerletResponse {

    private UUID id;

    private Instant ervenyessegKezdete;

    private Instant ervenyessegVege;

    private UserResponse tulajdonos;

    private ErvenyessegZona ervenyessegZona;

    private int ar;

}
