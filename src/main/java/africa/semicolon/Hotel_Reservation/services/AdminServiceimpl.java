package africa.semicolon.Hotel_Reservation.services;

import africa.semicolon.Hotel_Reservation.dtos.requests.*;
import africa.semicolon.Hotel_Reservation.dtos.responses.*;
import africa.semicolon.Hotel_Reservation.exceptions.AlreadyExistException;
import africa.semicolon.Hotel_Reservation.exceptions.IllegalParametersException;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.models.Admin;
import africa.semicolon.Hotel_Reservation.models.Customer;
import africa.semicolon.Hotel_Reservation.models.Reservation;
import africa.semicolon.Hotel_Reservation.models.Room;
import africa.semicolon.Hotel_Reservation.repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AdminServiceimpl implements AdminServiceInterface{
    private final AdminRepository adminRepository;
    private final RoomServiceInterface roomServiceInterface;
    private final ReservationServiceInterface reservationServiceInterface;

    @Override
    public RegistrationResponse registerAdmin(RegistrationRequest registrationRequest) {
        Admin admin = new Admin();
        admin.setFirstName(registrationRequest.getFirstName());
        admin.setLastName(registrationRequest.getLastName());
        admin.setEmail(registrationRequest.getEmail());
        admin.setPhoneNumber(registrationRequest.getPhoneNumber());
        admin.setPassword(registrationRequest.getPassword());
        Admin savedAdmin = adminRepository.save(admin);
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setMessage("registration successful");
        return registrationResponse;
    }

    @Override
    public LoginResponse loginAdmin(LoginRequest loginRequest) throws NotFoundException, IllegalParametersException {
        Admin foundAdmin = adminRepository.findAdminByEmail(loginRequest.getEmail());
        if (foundAdmin==null) throw new NotFoundException("Admin was not found, try putting in the correct email");
        if (!Objects.equals(foundAdmin.getPassword(), loginRequest.getPassword())) throw new IllegalParametersException("incorrect password");
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setFirstName(foundAdmin.getFirstName());
        loginResponse.setLastName(foundAdmin.getLastName());
        return loginResponse;
    }

    @Override
    public UpdateResponse updateAdmin(String email, UpdateRequest updateRequest) throws NotFoundException {
        Admin foundAdmin = adminRepository.findAdminByEmail(email);
        if (foundAdmin==null) throw new NotFoundException("Admin was not found");
        if (updateRequest.getFirstName()!=null){foundAdmin.setFirstName(updateRequest.getFirstName());}
        if (updateRequest.getLastName()!=null){foundAdmin.setLastName(updateRequest.getLastName());}
        if (updateRequest.getPassword()!=null){foundAdmin.setPassword(updateRequest.getPassword());}
        if (updateRequest.getPhoneNumber()!=null){foundAdmin.setPhoneNumber(updateRequest.getPhoneNumber());}
        adminRepository.save(foundAdmin);
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setFirstName(foundAdmin.getFirstName());
        updateResponse.setLastName(foundAdmin.getLastName());
        updateResponse.setPassword(foundAdmin.getPassword());
        updateResponse.setPhoneNumber(foundAdmin.getPhoneNumber());
        return updateResponse;
    }

    @Override
    public DeleteResponse deleteAccount(DeleteRequest deleteRequest) throws NotFoundException {
        Admin foundAdmin = adminRepository.findAdminByEmail(deleteRequest.getEmail());
        if (foundAdmin==null) throw new NotFoundException("Admin was not found");
        adminRepository.delete(foundAdmin);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("delete successful");
        return deleteResponse;
    }
//    the things pertaining to the roomService

    @Override
    public RoomResponse makeRoomAvailable(AvailableRequest availableRequest) throws NotFoundException {
        return roomServiceInterface.makeRoomAvailable(availableRequest);
    }

    @Override
    public RegistrationResponse createRoom(RoomCreationRequest roomCreationRequest) throws AlreadyExistException {
        return roomServiceInterface.createRoom(roomCreationRequest);
    }

    @Override
    public RoomResponse findRoomByRoomNumber(int roomNumber) {
        return roomServiceInterface.findRoomByRoomNumber(roomNumber);
    }

    @Override
    public DeleteResponse deleteRoomByRoomNumber(int roomNumber) throws NotFoundException {
        return roomServiceInterface.deleteRoomByRoomNumber(roomNumber);
    }

    @Override
    public List<Room> findAllRooms() {
        return roomServiceInterface.findAllRooms();
    }

    @Override
    public DeleteResponse deleteAllRooms() {
        return roomServiceInterface.deleteAllRooms();
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationServiceInterface.findAllReservations();
    }

    @Override
    public List<Reservation> findAllReservationsByCustomerEmail(String email) throws NotFoundException {
        return reservationServiceInterface.findAllReservationsByCustomerEmail(email);
    }

    @Override
    public DeleteResponse deleteAllReservations() {
        return reservationServiceInterface.deleteAllReservations();
    }

    @Override
    public DeleteResponse deleteReservationsByCustomerEmail(String email) throws NotFoundException {
        return reservationServiceInterface.deleteReservationsByCustomerEmail(email);
    }

    @Override
    public List<Room> findAllAvailableRooms() {
        return roomServiceInterface.findAllAvailableRoom();
    }

}
