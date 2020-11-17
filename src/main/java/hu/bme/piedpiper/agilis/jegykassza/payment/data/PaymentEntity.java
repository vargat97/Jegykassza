package hu.bme.piedpiper.agilis.jegykassza.payment.data;

import hu.bme.piedpiper.agilis.jegykassza.util.PaymentStatus;
import hu.bme.piedpiper.agilis.jegykassza.util.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    private UUID id;

    @Column
    private UUID productId;

    @Column
    private String userId;

    @Column
    private Instant paymentTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

}
