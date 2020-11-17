package hu.bme.piedpiper.agilis.jegykassza;

import hu.bme.piedpiper.agilis.jegykassza.berlet.data.BerletEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.berlet.service.BerletService;
import hu.bme.piedpiper.agilis.jegykassza.jegy.data.JegyEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.jegy.service.JegyService;
import hu.bme.piedpiper.agilis.jegykassza.payment.data.PaymentEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.payment.service.PaymentService;
import hu.bme.piedpiper.agilis.jegykassza.user.api.UserCreateRequest;
import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntity;
import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JegykasszaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class JegykasszaApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private JegyService jegyService;

    @Autowired
    private BerletService berletService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private JegyEntityRepository jegyEntityRepository;

    @Autowired
    private BerletEntityRepository berletEntityRepository;

    @Autowired
    private PaymentEntityRepository paymentEntityRepository;

    @Before
    public void clear() {
        paymentEntityRepository.deleteAll();
        jegyEntityRepository.deleteAll();
        berletEntityRepository.deleteAll();
        userEntityRepository.deleteAll();
    }

    private UserCreateRequest createUserRequest() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setEmailAddress("test@emailaddress.hu");
        userCreateRequest.setPassword("testpassword");
        userCreateRequest.setUserName("testusername");
        return userCreateRequest;
    }

    @Test
    public void testCreateUser() {
        Assert.assertEquals(0, userEntityRepository.count());
        UserEntity entity = userService.registerUser(createUserRequest());
        Assert.assertEquals(1, userEntityRepository.count());
        Assert.assertTrue(userEntityRepository.findById(entity.getId()).isPresent());
    }

    @Test
    void contextLoads() {
    }

}
