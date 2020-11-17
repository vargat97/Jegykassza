package hu.bme.piedpiper.agilis.jegykassza.berlet.controller;

import hu.bme.piedpiper.agilis.jegykassza.berlet.api.AvailableBerlet;
import hu.bme.piedpiper.agilis.jegykassza.berlet.api.BerletResponse;
import hu.bme.piedpiper.agilis.jegykassza.berlet.api.BerletVasarlasRequest;
import hu.bme.piedpiper.agilis.jegykassza.berlet.service.BerletResponseMapper;
import hu.bme.piedpiper.agilis.jegykassza.berlet.service.BerletService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/berlet-controller")
@AllArgsConstructor
public class BerletController {

    private final BerletService berletService;

    private final BerletResponseMapper berletResponseMapper;

    @PostMapping("/vasarlas/{userId}")
    public BerletResponse berletVasarlas(@PathVariable UUID userId, BerletVasarlasRequest berletVasarlasRequest) {
        return berletResponseMapper.map(berletService.berletVasarlas(berletVasarlasRequest, userId));
    }

    @GetMapping("/elerheto-berletek")
    public AvailableBerlet getAllBerlet(){
        return berletResponseMapper.mapBerletList(berletService.getAllBerlet());
    }

}
