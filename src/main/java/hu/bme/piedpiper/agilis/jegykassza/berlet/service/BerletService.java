package hu.bme.piedpiper.agilis.jegykassza.berlet.service;

import hu.bme.piedpiper.agilis.jegykassza.berlet.api.BerletVasarlasRequest;
import hu.bme.piedpiper.agilis.jegykassza.berlet.data.BerletEntity;
import hu.bme.piedpiper.agilis.jegykassza.berlet.data.BerletEntityRepository;
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
public class BerletService {

    private final BerletEntityRepository berletRepository;

    private final UserService userService;

    public BerletEntity berletVasarlas(BerletVasarlasRequest berletVasarlasRequest, UUID userId) {
        UserEntity user = userService.getUser(userId);
        LocalDate startDate = LocalDate.ofInstant(berletVasarlasRequest.getErvenyessegKezdete(), ZoneId.of("Europe/Budapest"));
        BerletEntity berletEntity = new BerletEntity();
        berletEntity.setErvenyessegKezdete(startDate.atStartOfDay(ZoneId.of("Europe/Budapest")).toInstant());
        berletEntity.setErvenyessegVege(startDate.plusMonths(1).atStartOfDay(ZoneId.of("Europe/Budapest")).toInstant());
        berletEntity.setPaymentStatus(PaymentStatus.PENDING);
        berletEntity.setErvenyessegZona(berletVasarlasRequest.getErvenyessegZona());
        berletEntity.setTulajdonos(user);
        return berletRepository.save(berletEntity);
    }

    public Map<ErvenyessegZona, Integer> getAllBerlet() {
        Map<ErvenyessegZona, Integer> map = new HashMap<>();
        Arrays.stream(ErvenyessegZona.values()).forEach(a -> map.put(a, 1000));
        return map;
    }
}
