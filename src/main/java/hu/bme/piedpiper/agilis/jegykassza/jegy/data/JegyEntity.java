package hu.bme.piedpiper.agilis.jegykassza.jegy.data;

import hu.bme.piedpiper.agilis.jegykassza.util.AbstractProduct;
import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Entity
@Getter
@Setter
public class JegyEntity extends AbstractProduct {

    @Column(nullable = false)
    private Instant ervenyessegKezdete;

    @Column
    private boolean used = false;

    @Enumerated(EnumType.STRING)
    private ErvenyessegZona ervenyessegZona;

    @Column
    private int ar = 1000;

}
