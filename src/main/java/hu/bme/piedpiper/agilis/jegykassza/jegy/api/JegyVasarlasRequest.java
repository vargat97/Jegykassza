package hu.bme.piedpiper.agilis.jegykassza.jegy.api;

import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import lombok.Data;

import java.time.Instant;

@Data
public class JegyVasarlasRequest {

    private Instant ervenyessegKezdete;

    private ErvenyessegZona ervenyessegZona;

}
