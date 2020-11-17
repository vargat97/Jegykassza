package hu.bme.piedpiper.agilis.jegykassza.payment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, UUID> {
}
