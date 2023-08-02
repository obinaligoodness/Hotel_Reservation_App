package africa.semicolon.Hotel_Reservation.services;

import africa.semicolon.Hotel_Reservation.dtos.requests.AvailableRequest;
import africa.semicolon.Hotel_Reservation.dtos.requests.BookRoomRequest;
import africa.semicolon.Hotel_Reservation.dtos.requests.ReservationRequest;
import africa.semicolon.Hotel_Reservation.dtos.requests.RoomCreationRequest;
import africa.semicolon.Hotel_Reservation.dtos.responses.DeleteResponse;
import africa.semicolon.Hotel_Reservation.dtos.responses.RegistrationResponse;
import africa.semicolon.Hotel_Reservation.dtos.responses.ReservationResponse;
import africa.semicolon.Hotel_Reservation.dtos.responses.RoomResponse;
import africa.semicolon.Hotel_Reservation.exceptions.AlreadyExistException;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.models.Room;

import java.util.List;

public interface RoomServiceInterface {
    RegistrationResponse createRoom(RoomCreationRequest roomCreationRequest) throws AlreadyExistException;
    RoomResponse findRoomByRoomNumber(int roomNumber);
    RoomResponse makeRoomAvailable(AvailableRequest availableRequest) throws NotFoundException;
    DeleteResponse deleteRoomByRoomNumber(int roomNumber) throws NotFoundException;
    List<Room> findAllRooms();
    DeleteResponse deleteAllRooms();
    List<Room> findAllAvailableRoom();


}
