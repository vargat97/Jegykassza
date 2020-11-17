package hu.bme.piedpiper.agilis.jegykassza.berlet.api;

import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class AvailableBerlet implements Serializable {

    private Map<ErvenyessegZona, Integer> elerhetoBerletek;

}
