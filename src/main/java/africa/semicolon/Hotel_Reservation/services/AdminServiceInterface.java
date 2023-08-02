package africa.semicolon.Hotel_Reservation.services;

import africa.semicolon.Hotel_Reservation.dtos.requests.*;
import africa.semicolon.Hotel_Reservation.dtos.responses.*;
import africa.semicolon.Hotel_Reservation.exceptions.AlreadyExistException;
import africa.semicolon.Hotel_Reservation.exceptions.IllegalParametersException;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.models.Reservation;
import africa.semicolon.Hotel_Reservation.models.Room;

import java.util.List;

public interface AdminServiceInterface {

    RegistrationResponse registerAdmin(RegistrationRequest registrationRequest);
    LoginResponse loginAdmin(LoginRequest loginRequest) throws NotFoundException, IllegalParametersException;

    UpdateResponse updateAdmin(String email, UpdateRequest updateRequest) throws NotFoundException;

    DeleteResponse deleteAccount(DeleteRequest deleteRequest) throws NotFoundException;
// methods for RoomService
    RoomResponse makeRoomAvailable(AvailableRequest availableRequest) throws NotFoundException;
    RegistrationResponse createRoom(RoomCreationRequest roomCreationRequest) throws AlreadyExistException;
    RoomResponse findRoomByRoomNumber(int roomNumber);
    DeleteResponse deleteRoomByRoomNumber(int roomNumber) throws NotFoundException;
    List<Room> findAllRooms();
    DeleteResponse deleteAllRooms();
//    methods for ReservationService
    List<Reservation> findAllReservations();
    List<Reservation> findAllReservationsByCustomerEmail(String Email) throws NotFoundException;
    DeleteResponse  deleteAllReservations();
    DeleteResponse deleteReservationsByCustomerEmail(String Email) throws NotFoundException;
    List<Room> findAllAvailableRooms();
}
