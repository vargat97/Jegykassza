package hu.bme.piedpiper.agilis.jegykassza.berlet.data;

import hu.bme.piedpiper.agilis.jegykassza.util.AbstractProduct;
import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import hu.bme.piedpiper.agilis.jegykassza.util.PaymentStatus;
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
public class BerletEntity extends AbstractProduct {

    @Column(nullable = false)
    private Instant ervenyessegKezdete;

    @Column(nullable = false)
    private Instant ervenyessegVege;

    @Enumerated(EnumType.STRING)
    private ErvenyessegZona ervenyessegZona;

    @Column
    private int ar = 1000;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
