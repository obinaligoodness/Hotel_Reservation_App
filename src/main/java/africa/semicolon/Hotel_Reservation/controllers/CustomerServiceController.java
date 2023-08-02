package africa.semicolon.Hotel_Reservation.controllers;

import africa.semicolon.Hotel_Reservation.dtos.requests.*;
import africa.semicolon.Hotel_Reservation.dtos.responses.*;
import africa.semicolon.Hotel_Reservation.exceptions.IllegalParametersException;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.models.Room;
import africa.semicolon.Hotel_Reservation.services.CustomerServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/customer")
public class CustomerServiceController {
    private final CustomerServiceInterface customerServiceInterface;

    @PostMapping("/register")
    public RegistrationResponse customerRegistration(@RequestBody RegistrationRequest registrationRequest){
        RegistrationResponse registeredCustomerResponse = customerServiceInterface.registerCustomer(registrationRequest);
        return registeredCustomerResponse;
    }
    @PostMapping("/login")
    public LoginResponse loginCustomer(@RequestBody LoginRequest loginRequest) throws NotFoundException, IllegalParametersException {
        LoginResponse loggedInCustomer = customerServiceInterface.loginCustomer(loginRequest);
        return loggedInCustomer;
    }
    @PostMapping("/update/{email}")
    public UpdateResponse updateCustomer(@PathVariable String email, @RequestBody UpdateRequest updateRequest) throws NotFoundException {
        UpdateResponse updatedCustomer = customerServiceInterface.updateCustomer(email, updateRequest);
        return updatedCustomer;
    }
    @PostMapping("/delete")
    public DeleteResponse deleteCustomerAccount(@RequestBody DeleteRequest deleteRequest) throws NotFoundException {
        DeleteResponse deletedCustomer = customerServiceInterface.deleteAccount(deleteRequest);
        return deletedCustomer;
    }
    @PostMapping("/availableRooms")
    public List<Room> findAllAvailableRooms(){
        return customerServiceInterface.findAllAvailableRooms();
    }
    @PostMapping("/bookRoom")
    public ReservationResponse bookRoom(@RequestBody BookRoomRequest bookRoomRequest) throws NotFoundException {
        return customerServiceInterface.bookRoom(bookRoomRequest);
    }

}
