package hu.bme.piedpiper.agilis.jegykassza.jegy.service;

import hu.bme.piedpiper.agilis.jegykassza.jegy.api.AvailableJegy;
import hu.bme.piedpiper.agilis.jegykassza.jegy.api.JegyResponse;
import hu.bme.piedpiper.agilis.jegykassza.jegy.data.JegyEntity;
import hu.bme.piedpiper.agilis.jegykassza.user.api.UserResponse;
import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class JegyResponseMapper {
    public JegyResponse map(JegyEntity entity) {
        JegyResponse jegyResponse = new JegyResponse();
        jegyResponse.setAr(entity.getAr());
        jegyResponse.setErvenyessegKezdete(entity.getErvenyessegKezdete());
        jegyResponse.setUsed(entity.isUsed());
        jegyResponse.setTulajdonos(new UserResponse(entity.getTulajdonos().getId(), entity.getTulajdonos().getUsername()));
        jegyResponse.setErvenyessegZona(entity.getErvenyessegZona());
        jegyResponse.setId(entity.getId());
        return jegyResponse;
    }

    public AvailableJegy mapJegyList(Map<ErvenyessegZona, Integer> allJegy) {
        AvailableJegy availableJegy = new AvailableJegy();
        availableJegy.setElerhetoJegyek(allJegy);
        return availableJegy;
    }
}
