package africa.semicolon.Hotel_Reservation.services;

import africa.semicolon.Hotel_Reservation.dtos.requests.*;
import africa.semicolon.Hotel_Reservation.dtos.responses.*;
import africa.semicolon.Hotel_Reservation.exceptions.IllegalParametersException;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.models.Customer;
import africa.semicolon.Hotel_Reservation.models.Reservation;
import africa.semicolon.Hotel_Reservation.models.Room;
import africa.semicolon.Hotel_Reservation.models.RoomType;
import africa.semicolon.Hotel_Reservation.repositories.CustomerRepository;
import africa.semicolon.Hotel_Reservation.repositories.ReservationRepository;
import africa.semicolon.Hotel_Reservation.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerServiceInterface{
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    ReservationServiceInterface reservationServiceInterface;
    @Override
    public RegistrationResponse registerCustomer(RegistrationRequest registrationRequest) {
        Customer customer = new Customer();
        customer.setFirstName(registrationRequest.getFirstName());
        customer.setLastName(registrationRequest.getLastName());
        customer.setEmail(registrationRequest.getEmail());
        customer.setPhoneNumber(registrationRequest.getPhoneNumber());
        customer.setPassword(registrationRequest.getPassword());
        customerRepository.save(customer);
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setMessage("registration successful");
        return registrationResponse;
    }

    @Override
    public LoginResponse loginCustomer(LoginRequest loginRequest) throws NotFoundException, IllegalParametersException {
        Customer foundCustomer = customerRepository.findCustomerByEmail(loginRequest.getEmail());
        if (foundCustomer==null) throw new NotFoundException("Customer was not found, try putting in the correct email");
        if (!Objects.equals(foundCustomer.getPassword(), loginRequest.getPassword())) throw new IllegalParametersException("incorrect password");
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setFirstName(foundCustomer.getFirstName());
        loginResponse.setLastName(foundCustomer.getLastName());
        return loginResponse;
    }

    @Override
    public UpdateResponse updateCustomer(String email, UpdateRequest updateRequest) throws NotFoundException {
        Customer foundCustomer = customerRepository.findCustomerByEmail(email);
        if (foundCustomer==null) throw new NotFoundException("Customer was not found");
        if (updateRequest.getFirstName()!=null){foundCustomer.setFirstName(updateRequest.getFirstName());}
        if (updateRequest.getLastName()!=null){foundCustomer.setLastName(updateRequest.getLastName());}
        if (updateRequest.getPassword()!=null){foundCustomer.setPassword(updateRequest.getPassword());}
        if (updateRequest.getPhoneNumber()!=null){foundCustomer.setPhoneNumber(updateRequest.getPhoneNumber());}
        customerRepository.save(foundCustomer);
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setFirstName(foundCustomer.getFirstName());
        updateResponse.setLastName(foundCustomer.getLastName());
        updateResponse.setPassword(foundCustomer.getPassword());
        updateResponse.setPhoneNumber(foundCustomer.getPhoneNumber());
        return updateResponse;

    }

    @Override
    public DeleteResponse deleteAccount(DeleteRequest deleteRequest) throws NotFoundException {
        Customer foundCustomer = customerRepository.findCustomerByEmail(deleteRequest.getEmail());
        if (foundCustomer==null) throw new NotFoundException("Customer was not found");
        customerRepository.delete(foundCustomer);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("delete successful");
        return deleteResponse;
    }

    @Override
    public Customer findCustomerByEmail(String email) throws NotFoundException {
        Customer foundCustomer = customerRepository.findCustomerByEmail(email);
        if (foundCustomer==null) throw new NotFoundException("Customer not found");
        return foundCustomer;
    }

    @Override
    public List<Room> findAllAvailableRooms() {
        return roomRepository.findRoomByStatus(true);
    }

    @Override
    public ReservationResponse bookRoom(BookRoomRequest bookRoomRequest) throws NotFoundException {
        Customer foundCustomer = findCustomerByEmail(bookRoomRequest.getEmail());
        if (foundCustomer==null)throw new NotFoundException("Customer does not exist");
        Room foundRoom = roomRepository.findRoomByRoomNumber(bookRoomRequest.getRoomNumber());
        if (foundRoom==null) throw new NotFoundException("Room number does not exist");
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setCustomerId(foundCustomer.getCustomerId());
        reservationRequest.setPassword(foundCustomer.getPassword());
        reservationRequest.setEmail(foundCustomer.getEmail());
        reservationRequest.setFirstName(foundCustomer.getFirstName());
        reservationRequest.setLastName(foundCustomer.getLastName());
        reservationRequest.setPhoneNumber(foundCustomer.getPhoneNumber());
        reservationRequest.setRoomId(foundRoom.getRoomId());
        reservationRequest.setRoomNumber(foundRoom.getRoomNumber());
        reservationRequest.setRoomType(foundRoom.getRoomType());
        if (reservationRequest.getRoomType().equals(RoomType.SINGLE)){
        reservationRequest.setPrice(BigDecimal.valueOf(10000));}else{reservationRequest.setPrice(BigDecimal.valueOf(20000));}
        reservationRequest.setStatus(foundRoom.isStatus());
        foundRoom.setStatus(false);
        roomRepository.save(foundRoom);
        return reservationServiceInterface.saveReservations(reservationRequest);
    }
}
