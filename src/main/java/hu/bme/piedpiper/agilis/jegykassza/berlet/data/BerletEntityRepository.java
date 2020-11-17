package hu.bme.piedpiper.agilis.jegykassza.berlet.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BerletEntityRepository extends JpaRepository<BerletEntity, UUID> {
}
