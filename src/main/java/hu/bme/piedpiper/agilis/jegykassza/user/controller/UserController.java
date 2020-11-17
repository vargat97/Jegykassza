package hu.bme.piedpiper.agilis.jegykassza.user.controller;

import hu.bme.piedpiper.agilis.jegykassza.user.api.UserCreateRequest;
import hu.bme.piedpiper.agilis.jegykassza.user.api.UserResponse;
import hu.bme.piedpiper.agilis.jegykassza.user.service.UserResponseMapper;
import hu.bme.piedpiper.agilis.jegykassza.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/user-controller")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserResponseMapper userResponseMapper;

    @PostMapping(value = "/register")
    public UserResponse registerUser(UserCreateRequest userCreateRequest) {
        return userResponseMapper.map(userService.registerUser(userCreateRequest));
    }

}
