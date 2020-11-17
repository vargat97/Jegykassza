package hu.bme.piedpiper.agilis.jegykassza.user.service;

import hu.bme.piedpiper.agilis.jegykassza.user.api.UserCreateRequest;
import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntity;
import hu.bme.piedpiper.agilis.jegykassza.user.data.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity registerUser(UserCreateRequest userCreateRequest) {
        UserEntity entity = new UserEntity();
        entity.setEmail(userCreateRequest.getEmailAddress());
        entity.setPassword(userCreateRequest.getPassword());
        entity.setUsername(userCreateRequest.getUserName());
        return userRepository.save(entity);
    }

    public UserEntity getUser(UUID id, String password) {
        return userRepository.findByIdAndPassword(id, password).orElseThrow(EntityNotFoundException::new);
    }

    public UserEntity getUser(UUID id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
