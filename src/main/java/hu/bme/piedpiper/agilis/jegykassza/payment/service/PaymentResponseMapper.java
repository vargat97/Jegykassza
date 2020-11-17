package hu.bme.piedpiper.agilis.jegykassza.payment.service;

import hu.bme.piedpiper.agilis.jegykassza.payment.api.PaymentResponse;
import hu.bme.piedpiper.agilis.jegykassza.payment.data.PaymentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentResponseMapper {

    public PaymentResponse map(PaymentEntity paymentEntity) {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(paymentEntity.getId());
        response.setPaymentStatus(paymentEntity.getPaymentStatus());
        response.setProductId(paymentEntity.getProductId());
        return response;
    }

    public Page<PaymentResponse> mapPage(Page<PaymentEntity> page) {
        List<PaymentResponse> collect = page.getContent().stream().map(c -> {
            PaymentResponse response = new PaymentResponse();
            response.setProductId(c.getProductId());
            response.setPaymentStatus(response.getPaymentStatus());
            response.setPaymentId(response.getPaymentId());
            return response;
        }).collect(Collectors.toList());
        return new PageImpl<>(collect, page.getPageable(), page.getTotalElements());
    }
}
