package hu.bme.piedpiper.agilis.jegykassza.payment.api;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public abstract class AbstractPaymentRequest implements Serializable {

    private UUID productId;

}
