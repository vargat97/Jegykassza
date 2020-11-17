package hu.bme.piedpiper.agilis.jegykassza.payment.api;

import lombok.Data;

@Data
public class CashPaymentRequest extends AbstractPaymentRequest {

    private int paidAmmount;

}
