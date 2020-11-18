package hu.bme.piedpiper.agilis.jegykassza;

import hu.bme.piedpiper.agilis.jegykassza.berlet.api.BerletVasarlasRequest;
import hu.bme.piedpiper.agilis.jegykassza.berlet.data.BerletEntity;
import hu.bme.piedpiper.agilis.jegykassza.berlet.data.BerletEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.berlet.service.BerletService;
import hu.bme.piedpiper.agilis.jegykassza.jegy.api.JegyVasarlasRequest;
import hu.bme.piedpiper.agilis.jegykassza.jegy.data.JegyEntity;
import hu.bme.piedpiper.agilis.jegykassza.jegy.data.JegyEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.jegy.service.JegyService;
import hu.bme.piedpiper.agilis.jegykassza.payment.api.CashPaymentRequest;
import hu.bme.piedpiper.agilis.jegykassza.payment.api.CreditCardPaymentRequest;
import hu.bme.piedpiper.agilis.jegykassza.payment.api.PaypalPaymentRequest;
import hu.bme.piedpiper.agilis.jegykassza.payment.data.PaymentEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.payment.service.PaymentService;
import hu.bme.piedpiper.agilis.jegykassza.user.api.UserCreateRequest;
import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntity;
import hu.bme.piedpiper.agilis.jegykassza.user.data.UserEntityRepository;
import hu.bme.piedpiper.agilis.jegykassza.user.service.UserService;
import hu.bme.piedpiper.agilis.jegykassza.util.ErvenyessegZona;
import hu.bme.piedpiper.agilis.jegykassza.util.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
    @After
    public void clear() {
        userEntityRepository.deleteAll();
        paymentEntityRepository.deleteAll();
        jegyEntityRepository.deleteAll();
        berletEntityRepository.deleteAll();
    }

    private UserCreateRequest createUserRequest() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setEmailAddress("test@emailaddress.hu");
        userCreateRequest.setPassword("testpassword");
        userCreateRequest.setUserName("testusername");
        return userCreateRequest;
    }

    private BerletVasarlasRequest createBerletRequest() {
        BerletVasarlasRequest vasarlasRequest = new BerletVasarlasRequest();
        vasarlasRequest.setErvenyessegKezdete(Instant.now());
        vasarlasRequest.setErvenyessegZona(ErvenyessegZona.ORSZAGOS);
        return vasarlasRequest;
    }


    private JegyVasarlasRequest createJegyRequest() {
        JegyVasarlasRequest vasarlasRequest = new JegyVasarlasRequest();
        vasarlasRequest.setErvenyessegKezdete(Instant.now());
        vasarlasRequest.setErvenyessegZona(ErvenyessegZona.ORSZAGOS);
        return vasarlasRequest;
    }

    private CreditCardPaymentRequest createCreditCardRequest(UUID productId) {
        CreditCardPaymentRequest creditCardPaymentRequest = new CreditCardPaymentRequest();
        creditCardPaymentRequest.setCcv("888");
        creditCardPaymentRequest.setCreditCardExpiryDate(Date.from(Instant.now()));
        creditCardPaymentRequest.setCreditCardNumber("4716035106731071");
        creditCardPaymentRequest.setProductId(productId);
        return creditCardPaymentRequest;
    }

    private CashPaymentRequest createCashRequest(UUID productId) {
        CashPaymentRequest cashPaymentRequest = new CashPaymentRequest();
        cashPaymentRequest.setPaidAmmount(1500);
        cashPaymentRequest.setProductId(productId);
        return cashPaymentRequest;
    }

    private PaypalPaymentRequest createPaypalRequest(UUID productId) {
        PaypalPaymentRequest paypalPaymentRequest = new PaypalPaymentRequest();
        paypalPaymentRequest.setPaypalUsername("TestPaypalUsername");
        paypalPaymentRequest.setProductId(productId);
        return paypalPaymentRequest;
    }

    @Test
    @Transactional
    void testCreateUser() {
        Assert.assertEquals(0, userEntityRepository.count());
        UserEntity entity = userService.registerUser(createUserRequest());
        Assert.assertEquals(1, userEntityRepository.count());
        Assert.assertTrue(userEntityRepository.findById(entity.getId()).isPresent());
    }

    @Test
    @Transactional
    void testReadAllAvailableBerlet() {
        Map<ErvenyessegZona, Integer> berletek = berletService.getAllBerlet();
        Assert.assertFalse(berletek.isEmpty());
        Assert.assertTrue(berletek.containsKey(ErvenyessegZona.BUDAPEST));
        Assert.assertEquals(1000, berletek.get(ErvenyessegZona.BUDAPEST).intValue());

    }

    @Test
    @Transactional
    void testReadAllAvailableJegy() {
        Map<ErvenyessegZona, Integer> berletek = jegyService.getAllJegy();
        Assert.assertFalse(berletek.isEmpty());
        Assert.assertTrue(berletek.containsKey(ErvenyessegZona.BUDAPEST));
        Assert.assertEquals(200, berletek.get(ErvenyessegZona.BUDAPEST).intValue());
    }

    @Test
    @Transactional
    void testBuyBerlet() {
        UserEntity user = userService.registerUser(createUserRequest());
        Assert.assertEquals(0, berletEntityRepository.count());
        BerletEntity berletEntity = berletService.berletVasarlas(createBerletRequest(), user.getId());
        Assert.assertEquals(1, berletEntityRepository.count());
        Assert.assertTrue(berletEntityRepository.findById(berletEntity.getId()).isPresent());
    }

    @Test
    @Transactional
    void testBuyJegy() {
        UserEntity user = userService.registerUser(createUserRequest());
        Assert.assertEquals(0, jegyEntityRepository.count());
        JegyEntity jegyEntity = jegyService.jegyVasarlas(createJegyRequest(), user.getId());
        Assert.assertEquals(1, jegyEntityRepository.count());
        Assert.assertTrue(jegyEntityRepository.findById(jegyEntity.getId()).isPresent());
    }

    @Test
    @Transactional
    void testFizetesByCard() {
        UserEntity user = userService.registerUser(createUserRequest());
        Assert.assertEquals(0, jegyEntityRepository.count());
        JegyEntity jegyEntity = jegyService.jegyVasarlas(createJegyRequest(), user.getId());
        Assert.assertEquals(1, jegyEntityRepository.count());
        Assert.assertTrue(jegyEntityRepository.findById(jegyEntity.getId()).isPresent());
        Assert.assertEquals(PaymentStatus.PENDING, jegyEntity.getPaymentStatus());
        Assert.assertEquals(0, paymentEntityRepository.count());
        paymentService.payByCard(createCreditCardRequest(jegyEntity.getId()));
        Assert.assertEquals(1, paymentEntityRepository.count());
        JegyEntity updated = jegyEntityRepository.findById(jegyEntity.getId()).get();
        Assert.assertEquals(PaymentStatus.SUCCESSFUL, updated.getPaymentStatus());
    }

    @Test
    @Transactional
    void testFizetesByPaypal() {
        UserEntity user = userService.registerUser(createUserRequest());
        Assert.assertEquals(0, jegyEntityRepository.count());
        JegyEntity jegyEntity = jegyService.jegyVasarlas(createJegyRequest(), user.getId());
        Assert.assertEquals(1, jegyEntityRepository.count());
        Assert.assertTrue(jegyEntityRepository.findById(jegyEntity.getId()).isPresent());
        Assert.assertEquals(PaymentStatus.PENDING, jegyEntity.getPaymentStatus());
        Assert.assertEquals(0, paymentEntityRepository.count());
        paymentService.payByPaypal(createPaypalRequest(jegyEntity.getId()));
        Assert.assertEquals(1, paymentEntityRepository.count());
        JegyEntity updated = jegyEntityRepository.findById(jegyEntity.getId()).get();
        Assert.assertEquals(PaymentStatus.SUCCESSFUL, updated.getPaymentStatus());
    }

    @Test
    @Transactional
    void testFizetesByCash() {
        UserEntity user = userService.registerUser(createUserRequest());
        Assert.assertEquals(0, jegyEntityRepository.count());
        JegyEntity jegyEntity = jegyService.jegyVasarlas(createJegyRequest(), user.getId());
        Assert.assertEquals(1, jegyEntityRepository.count());
        Assert.assertTrue(jegyEntityRepository.findById(jegyEntity.getId()).isPresent());
        Assert.assertEquals(PaymentStatus.PENDING, jegyEntity.getPaymentStatus());
        Assert.assertEquals(0, paymentEntityRepository.count());
        paymentService.payByCash(createCashRequest(jegyEntity.getId()));
        Assert.assertEquals(1, paymentEntityRepository.count());
        JegyEntity updated = jegyEntityRepository.findById(jegyEntity.getId()).get();
        Assert.assertEquals(PaymentStatus.SUCCESSFUL, updated.getPaymentStatus());
    }

}
