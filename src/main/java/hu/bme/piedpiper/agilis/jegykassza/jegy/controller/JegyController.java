package hu.bme.piedpiper.agilis.jegykassza.jegy.controller;

import hu.bme.piedpiper.agilis.jegykassza.jegy.api.AvailableJegy;
import hu.bme.piedpiper.agilis.jegykassza.jegy.api.JegyResponse;
import hu.bme.piedpiper.agilis.jegykassza.jegy.api.JegyVasarlasRequest;
import hu.bme.piedpiper.agilis.jegykassza.jegy.service.JegyResponseMapper;
import hu.bme.piedpiper.agilis.jegykassza.jegy.service.JegyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/jegy-controller")
@AllArgsConstructor
public class JegyController {

    private final JegyService jegyService;

    private final JegyResponseMapper jegyResponseMapper;

    @PostMapping("/vasarlas/{userId}")
    public JegyResponse jegyVasarlas(@PathVariable UUID userId, JegyVasarlasRequest jegyVasarlasRequest) {
        return jegyResponseMapper.map(jegyService.jegyVasarlas(jegyVasarlasRequest, userId));
    }

    @GetMapping("/elerheto-jegyek")
    public AvailableJegy getAllJegy() {
        return jegyResponseMapper.mapJegyList(jegyService.getAllJegy());
    }

}
