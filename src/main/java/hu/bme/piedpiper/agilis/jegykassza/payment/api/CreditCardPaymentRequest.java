package hu.bme.piedpiper.agilis.jegykassza.payment.api;

import lombok.Data;

import java.util.Date;

@Data
public class CreditCardPaymentRequest extends AbstractPaymentRequest {

    private String creditCardNumber;

    private Date creditCardExpiryDate;

    private String ccv;

}
