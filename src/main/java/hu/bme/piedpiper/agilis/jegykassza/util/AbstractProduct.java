package hu.bme.piedpiper.agilis.jegykassza.util;

import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
public class AbstractProduct {

    @Id
    @Column(nullable = false, unique = true)
    private final UUID id = UUID.randomUUID();

    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity tulajdonos;

}
