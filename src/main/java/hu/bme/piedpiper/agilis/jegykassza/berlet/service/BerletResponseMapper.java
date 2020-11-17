package hu.bme.piedpiper.agilis.jegykassza.berlet.service;

import hu.bme.piedpiper.agilis.jegykassza.berlet.api.AvailableBerlet;
import hu.bme.piedpiper.agilis.jegykassza.berlet.api.BerletResponse;
import hu.bme.piedpiper.agilis.jegykassza.berlet.data.BerletEntity;
import hu.bme.piedpiper.agilis.jegykassza.user.api.UserResponse;
import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class BerletResponseMapper {
    public BerletResponse map(BerletEntity entity) {
        BerletResponse berletResponse = new BerletResponse();
        berletResponse.setAr(entity.getAr());
        berletResponse.setErvenyessegKezdete(entity.getErvenyessegKezdete());
        berletResponse.setErvenyessegVege(entity.getErvenyessegVege());
        berletResponse.setTulajdonos(new UserResponse(entity.getTulajdonos().getId(), entity.getTulajdonos().getUsername()));
        berletResponse.setErvenyessegZona(entity.getErvenyessegZona());
        berletResponse.setId(entity.getId());
        return berletResponse;
    }

    public AvailableBerlet mapBerletList(Map<ErvenyessegZona, Integer> allBerlet) {
        AvailableBerlet availableBerlet = new AvailableBerlet();
        availableBerlet.setElerhetoBerletek(allBerlet);
        return availableBerlet;
    }
}
