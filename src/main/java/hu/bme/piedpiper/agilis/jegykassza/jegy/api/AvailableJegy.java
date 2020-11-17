package hu.bme.piedpiper.agilis.jegykassza.jegy.api;

import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class AvailableJegy implements Serializable {

    private Map<ErvenyessegZona, Integer> elerhetoJegyek;

}
