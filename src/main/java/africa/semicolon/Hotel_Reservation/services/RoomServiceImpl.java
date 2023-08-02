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
import africa.semicolon.Hotel_Reservation.models.Customer;
import africa.semicolon.Hotel_Reservation.models.Room;
import africa.semicolon.Hotel_Reservation.models.RoomType;
import africa.semicolon.Hotel_Reservation.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomServiceInterface{
    private final RoomRepository roomRepository;
    private final CustomerServiceInterface customerServiceInterface;
    @Override
    public RegistrationResponse createRoom(RoomCreationRequest roomCreationRequest) throws AlreadyExistException {
        Room room = new Room();
        room.setStatus(true);
        room.setRoomType(roomCreationRequest.getRoomType());
        var foundRoom = roomRepository.findRoomByRoomNumber(roomCreationRequest.getRoomNumber());
        if (foundRoom!=null) throw new AlreadyExistException("Room with room number already exists");
        room.setRoomNumber(roomCreationRequest.getRoomNumber());
        if (roomCreationRequest.getRoomType().equals(RoomType.SINGLE)){
        room.setPrice(BigDecimal.valueOf(10000));}else {room.setPrice(BigDecimal.valueOf(20000));}
        roomRepository.save(room);
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setMessage("Room Created Successfully");
        return registrationResponse;
    }

    @Override
    public RoomResponse findRoomByRoomNumber(int roomNumber) {
        Room foundRoom = roomRepository.findRoomByRoomNumber(roomNumber);
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setRoomType(foundRoom.getRoomType());
        roomResponse.setStatus(foundRoom.isStatus());
        roomResponse.setRoomNumber(foundRoom.getRoomNumber());
        roomResponse.setPrice(foundRoom.getPrice());
        return roomResponse;
    }

    @Override
    public RoomResponse makeRoomAvailable(AvailableRequest availableRequest) throws NotFoundException {
        Room foundRoom = roomRepository.findRoomByRoomNumber(availableRequest.getRoomNumber());
        if (foundRoom==null) throw new NotFoundException("room does not exist");
        foundRoom.setStatus(true);
        roomRepository.save(foundRoom);
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setRoomType(foundRoom.getRoomType());
        roomResponse.setStatus(foundRoom.isStatus());
        roomResponse.setRoomNumber(foundRoom.getRoomNumber());
        roomResponse.setPrice(foundRoom.getPrice());
        return roomResponse;
    }

    @Override
    public DeleteResponse deleteRoomByRoomNumber(int roomNumber) throws NotFoundException {
        Room foundRoom = roomRepository.findRoomByRoomNumber(roomNumber);
        if (foundRoom==null) throw new NotFoundException("room does not exist");
        roomRepository.delete(foundRoom);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("delete successful");
        return deleteResponse;
    }

    @Override
    public List<Room> findAllRooms() {
        List<Room> allRooms = roomRepository.findAll();
        return allRooms;
    }

    @Override
    public DeleteResponse deleteAllRooms() {
        List<Room> allRooms = findAllRooms();
        roomRepository.deleteAll(allRooms);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("delete successful");
        return deleteResponse;
    }

    @Override
    public List<Room> findAllAvailableRoom() {
        List<Room> allAvailableRoom = roomRepository.findRoomByStatus(true);
        return allAvailableRoom;
    }

}
