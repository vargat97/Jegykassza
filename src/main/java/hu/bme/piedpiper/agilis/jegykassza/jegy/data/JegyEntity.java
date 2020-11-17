package hu.bme.piedpiper.agilis.jegykassza.jegy.data;

import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntity;
import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import hu.bme.piedpiper.agilis.jegykassza.util.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class JegyEntity {

    @Id
    @Column(nullable = false, unique = true)
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private Instant ervenyessegKezdete;

    @Column
    private boolean used = false;

    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity tulajdonos;

    @Enumerated(EnumType.STRING)
    private ErvenyessegZona ervenyessegZona;

    @Column
    private int ar = 1000;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
