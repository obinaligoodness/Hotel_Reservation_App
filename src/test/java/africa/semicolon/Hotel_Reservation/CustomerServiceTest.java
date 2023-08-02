package africa.semicolon.Hotel_Reservation;

import africa.semicolon.Hotel_Reservation.dtos.requests.LoginRequest;
import africa.semicolon.Hotel_Reservation.dtos.requests.RegistrationRequest;
import africa.semicolon.Hotel_Reservation.exceptions.IllegalParametersException;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.services.CustomerServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerServiceInterface customerServiceInterface;

    @Test
    public void testThatACustomerCanRegisterSuccessfully(){
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setFirstName("Goodness");
        registrationRequest.setLastName("Obinali");
        registrationRequest.setEmail("obinaligoodness@gmail.com");
        registrationRequest.setPhoneNumber("08133571570");
        registrationRequest.setPassword("goodobin");
        assertNotNull(customerServiceInterface.registerCustomer(registrationRequest));
    }
    @Test
    public void testCustomerLogin() throws NotFoundException, IllegalParametersException {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("obinaligoodness@gmail.com");
        loginRequest.setPassword("goodobin");
        System.out.println(customerServiceInterface.loginCustomer(loginRequest));
        assertNotNull(customerServiceInterface.loginCustomer(loginRequest));
    }
}
