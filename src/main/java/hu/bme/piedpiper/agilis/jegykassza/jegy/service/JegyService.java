package hu.bme.piedpiper.agilis.jegykassza.jegy.service;

import hu.bme.piedpiper.agilis.jegykassza.jegy.api.JegyVasarlasRequest;
import hu.bme.piedpiper.agilis.jegykassza.jegy.data.JegyEntity;
import hu.bme.piedpiper.agilis.jegykassza.jegy.data.JegyRepository;
import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntity;
import hu.bme.piedpiper.agilis.jegykassza.user.service.UserService;
import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import hu.bme.piedpiper.agilis.jegykassza.util.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class JegyService {

    private final JegyRepository jegyRepository;

    private final UserService userService;

    public JegyEntity jegyVasarlas(JegyVasarlasRequest jegyVasarlasRequest, UUID userId) {
        UserEntity user = userService.getUser(userId);
        LocalDate startDate = LocalDate.ofInstant(jegyVasarlasRequest.getErvenyessegKezdete(), ZoneId.of("Europe/Budapest"));
        JegyEntity jegyEntity = new JegyEntity();
        jegyEntity.setErvenyessegKezdete(startDate.atStartOfDay(ZoneId.of("Europe/Budapest")).toInstant());
        jegyEntity.setUsed(false);
        jegyEntity.setPaymentStatus(PaymentStatus.PENDING);
        jegyEntity.setErvenyessegZona(jegyVasarlasRequest.getErvenyessegZona());
        jegyEntity.setTulajdonos(user);
        return jegyRepository.save(jegyEntity);
    }

    public Map<ErvenyessegZona, Integer> getAllJegy() {
        Map<ErvenyessegZona, Integer> map = new HashMap<>();
        Arrays.stream(ErvenyessegZona.values()).forEach(a -> map.put(a, 200));
        return map;
    }
}
