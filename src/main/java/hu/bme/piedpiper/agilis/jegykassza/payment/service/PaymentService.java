package hu.bme.piedpiper.agilis.jegykassza.payment.service;

import hu.bme.piedpiper.agilis.jegykassza.berlet.data.BerletEntity;
import hu.bme.piedpiper.agilis.jegykassza.berlet.data.BerletEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.jegy.data.JegyEntity;
import hu.bme.piedpiper.agilis.jegykassza.jegy.data.JegyEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.payment.api.CashPaymentRequest;
import hu.bme.piedpiper.agilis.jegykassza.payment.api.CreditCardPaymentRequest;
import hu.bme.piedpiper.agilis.jegykassza.payment.api.PaypalPaymentRequest;
import hu.bme.piedpiper.agilis.jegykassza.payment.data.PaymentEntity;
import hu.bme.piedpiper.agilis.jegykassza.payment.data.PaymentEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.util.AbstractProduct;
import hu.bme.piedpiper.agilis.jegykassza.util.PaymentStatus;
import hu.bme.piedpiper.agilis.jegykassza.util.PaymentType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentService {

    private final PaymentEntityRepository paymentEntityRepository;
    private final JegyEntityRepository jegyEntityRepository;
    private final BerletEntityRepository berletEntityRepository;

    public PaymentEntity payByCard(CreditCardPaymentRequest creditCardPaymentRequest) {
        AbstractProduct productById = findProductById(creditCardPaymentRequest.getProductId());
        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        payment.setPaymentTime(Instant.now());
        payment.setPaymentType(PaymentType.CREDIT_CARD);
        payment.setProductId(creditCardPaymentRequest.getProductId());
        payment.setUserId(productById.getTulajdonos().getId().toString());
        updateProductStatus(creditCardPaymentRequest.getProductId(), payment.getPaymentStatus());
        return paymentEntityRepository.save(payment);
    }

    public PaymentEntity payByPaypal(PaypalPaymentRequest paypalPaymentRequest) {
        findProductById(paypalPaymentRequest.getProductId());
        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        payment.setPaymentTime(Instant.now());
        payment.setPaymentType(PaymentType.PAYPAL);
        payment.setProductId(paypalPaymentRequest.getProductId());
        payment.setUserId(paypalPaymentRequest.getPaypalUsername());
        updateProductStatus(paypalPaymentRequest.getProductId(), payment.getPaymentStatus());
        return paymentEntityRepository.save(payment);
    }


    public PaymentEntity payByCash(CashPaymentRequest cashPaymentRequest) {
        AbstractProduct productById = findProductById(cashPaymentRequest.getProductId());
        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        payment.setPaymentTime(Instant.now());
        payment.setPaymentType(PaymentType.CASH);
        payment.setProductId(cashPaymentRequest.getProductId());
        payment.setUserId(productById.getTulajdonos().getId().toString());
        updateProductStatus(cashPaymentRequest.getProductId(), payment.getPaymentStatus());
        return paymentEntityRepository.save(payment);
    }

    public Page<PaymentEntity> getAllPayment(Pageable pageable) {
        return paymentEntityRepository.findAll(pageable);
    }

    private AbstractProduct findProductById(UUID productId) {
        Optional<BerletEntity> berletEntity = berletEntityRepository.findById(productId);
        Optional<JegyEntity> jegyEntity = jegyEntityRepository.findById(productId);
        if (berletEntity.isPresent() && berletEntity.get().getPaymentStatus() != PaymentStatus.SUCCESSFUL) {
            return berletEntity.get();
        } else if (jegyEntity.isPresent() && jegyEntity.get().getPaymentStatus() != PaymentStatus.SUCCESSFUL) {
            return jegyEntity.get();
        } else {
            throw new EntityNotFoundException("Nincs fizetésre váró termék az adott azonosítóval.");
        }
    }

    private void updateProductStatus(UUID productId, PaymentStatus paymentStatus) {
        Optional<BerletEntity> berletEntity = berletEntityRepository.findById(productId);
        Optional<JegyEntity> jegyEntity = jegyEntityRepository.findById(productId);
        if (berletEntity.isPresent() && berletEntity.get().getPaymentStatus() != PaymentStatus.SUCCESSFUL) {
            berletEntity.get().setPaymentStatus(paymentStatus);
            berletEntityRepository.save(berletEntity.get());
        } else if (jegyEntity.isPresent() && jegyEntity.get().getPaymentStatus() != PaymentStatus.SUCCESSFUL) {
            jegyEntity.get().setPaymentStatus(paymentStatus);
            jegyEntityRepository.save(jegyEntity.get());
        } else {
            throw new EntityNotFoundException("Nincs fizetésre váró termék az adott azonosítóval.");
        }
    }

}
