package hu.bme.piedpiper.agilis.jegykassza.jegy.api;

import hu.bme.piedpiper.agilis.jegykassza.user.api.UserResponse;
import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class JegyResponse {

    private UUID id;

    private Instant ervenyessegKezdete;

    private boolean used = false;

    private UserResponse tulajdonos;

    private ErvenyessegZona ervenyessegZona;

    private int ar;

}
