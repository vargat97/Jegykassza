package hu.bme.piedpiper.agilis.jegykassza.user.service;

import hu.bme.piedpiper.agilis.jegykassza.user.api.UserResponse;
import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserResponseMapper {
    public UserResponse map(UserEntity registerUser) {
        UserResponse response = new UserResponse();
        response.setUserId(registerUser.getId());
        response.setUsername(registerUser.getUsername());
        return response;
    }
}
