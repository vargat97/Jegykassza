package hu.bme.piedpiper.agilis.jegykassza.payment.api;

import lombok.Data;

@Data
public class PaypalPaymentRequest extends AbstractPaymentRequest {

    private String paypalUsername;

}
