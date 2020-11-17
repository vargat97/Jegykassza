package hu.bme.piedpiper.agilis.jegykassza.payment.controller;

import hu.bme.piedpiper.agilis.jegykassza.payment.api.CreditCardPaymentRequest;
import hu.bme.piedpiper.agilis.jegykassza.payment.api.PaymentResponse;
import hu.bme.piedpiper.agilis.jegykassza.payment.service.PaymentResponseMapper;
import hu.bme.piedpiper.agilis.jegykassza.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    private final PaymentResponseMapper paymentResponseMapper;

    @PostMapping("/creditCard")
    public PaymentResponse payByCreditCard(@RequestBody CreditCardPaymentRequest creditCardPaymentRequest) {
        return paymentResponseMapper.map(paymentService.payByCard(creditCardPaymentRequest));
    }

    @GetMapping("/getAll")
    public Page<PaymentResponse> getAllPayment(Pageable pageable) {
        return paymentResponseMapper.mapPage(paymentService.getAllPayment(pageable));
    }

}
