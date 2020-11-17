package hu.bme.piedpiper.agilis.jegykassza.berlet.api;

import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import lombok.Data;

import java.time.Instant;

@Data
public class BerletVasarlasRequest {

    private Instant ervenyessegKezdete;

    private ErvenyessegZona ervenyessegZona;

}
