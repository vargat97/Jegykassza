package hu.bme.piedpiper.agilis.jegykassza.payment.api;

import hu.bme.piedpiper.agilis.jegykassza.util.PaymentStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PaymentResponse implements Serializable {

    private PaymentStatus paymentStatus;

    private UUID productId;

    private UUID paymentId;

}
