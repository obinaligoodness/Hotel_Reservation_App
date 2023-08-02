package africa.semicolon.Hotel_Reservation.services;

import africa.semicolon.Hotel_Reservation.dtos.requests.*;
import africa.semicolon.Hotel_Reservation.dtos.responses.*;
import africa.semicolon.Hotel_Reservation.exceptions.IllegalParametersException;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.models.Customer;
import africa.semicolon.Hotel_Reservation.models.Room;

import java.util.List;

public interface CustomerServiceInterface {
    RegistrationResponse registerCustomer(RegistrationRequest registrationRequest);
    LoginResponse loginCustomer(LoginRequest loginRequest) throws NotFoundException, IllegalParametersException;

    UpdateResponse updateCustomer(String email, UpdateRequest updateRequest) throws NotFoundException;

    DeleteResponse deleteAccount(DeleteRequest deleteRequest) throws NotFoundException;
    Customer findCustomerByEmail(String email) throws NotFoundException;

    List<Room> findAllAvailableRooms();
    ReservationResponse bookRoom(BookRoomRequest bookRoomRequest) throws NotFoundException;
}
