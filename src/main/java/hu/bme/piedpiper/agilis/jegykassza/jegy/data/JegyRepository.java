package hu.bme.piedpiper.agilis.jegykassza.jegy.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JegyRepository extends JpaRepository<JegyEntity, UUID> {
}
