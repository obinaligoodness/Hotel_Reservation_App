package africa.semicolon.Hotel_Reservation.controllers;

import africa.semicolon.Hotel_Reservation.dtos.requests.*;
import africa.semicolon.Hotel_Reservation.dtos.responses.*;
import africa.semicolon.Hotel_Reservation.exceptions.AlreadyExistException;
import africa.semicolon.Hotel_Reservation.exceptions.IllegalParametersException;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.models.Reservation;
import africa.semicolon.Hotel_Reservation.models.Room;
import africa.semicolon.Hotel_Reservation.services.AdminServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/admin")
public class AdminServiceController {
    private final AdminServiceInterface adminServiceInterface;

    @PostMapping("/register")
    public RegistrationResponse registerAdmin(@RequestBody RegistrationRequest registrationRequest){
        RegistrationResponse registeredAdmin = adminServiceInterface.registerAdmin(registrationRequest);
        return registeredAdmin;
    }

    @GetMapping("/login")
    public LoginResponse loginAdmin(@RequestBody LoginRequest loginRequest) throws NotFoundException, IllegalParametersException {
        LoginResponse loggedInAdmin = adminServiceInterface.loginAdmin(loginRequest);
        return loggedInAdmin;
    }

    @PutMapping("/update/{email}")
    public UpdateResponse updateAdmin(@PathVariable String email, @RequestBody UpdateRequest updateRequest) throws NotFoundException {
        UpdateResponse updatedAdmin = adminServiceInterface.updateAdmin(email,updateRequest);
        return updatedAdmin;
    }

    @DeleteMapping("/delete")
    public DeleteResponse deleteAdminAccount(@RequestBody DeleteRequest deleteRequest) throws NotFoundException {
        DeleteResponse deletedAdmin = adminServiceInterface.deleteAccount(deleteRequest);
        return deletedAdmin;
    }

    @PostMapping("/createroom")
    public RegistrationResponse createRoom(@RequestBody RoomCreationRequest roomCreationRequest) throws AlreadyExistException {
        RegistrationResponse createdRoom = adminServiceInterface.createRoom(roomCreationRequest);
        return createdRoom;
    }

    @PostMapping("/makeAvailable")
    public RoomResponse makeRoomAvailable(@RequestBody AvailableRequest availableRequest) throws NotFoundException {
        RoomResponse availableRoom = adminServiceInterface.makeRoomAvailable(availableRequest);
        return availableRoom;
    }

    @GetMapping("/find/{roomNumber}")
    public RoomResponse findRoomByRoomNumber(@PathVariable int roomNumber){
        RoomResponse foundRoom = adminServiceInterface.findRoomByRoomNumber(roomNumber);
        return foundRoom;
    }

    @DeleteMapping("/delete/{roomNumber}")
    public DeleteResponse deleteRoomByRoomNumber(@PathVariable int roomNumber) throws NotFoundException {
        DeleteResponse deletedRoom = adminServiceInterface.deleteRoomByRoomNumber(roomNumber);
        return deletedRoom;
    }

    @GetMapping("/findAllRoom")
    public List<Room> findAllRoom(){
        List<Room> allRooms = adminServiceInterface.findAllRooms();
        return allRooms;
    }

    @DeleteMapping("/deleteAllRoom")
    public DeleteResponse deleteAllRooms(){
        return adminServiceInterface.deleteAllRooms();
    }

    @GetMapping("/findAllReservations")
    public List<Reservation> findAllReservations(){
        List<Reservation> allReservations = adminServiceInterface.findAllReservations();
        return allReservations;
    }

    @GetMapping("/reservation/{email}")
    public List<Reservation> findResrvationsByCustomerEmail(@PathVariable String email) throws NotFoundException {
        return adminServiceInterface.findAllReservationsByCustomerEmail(email);
    }

    @DeleteMapping("/deleteAllReservations")
    public DeleteResponse deleteAllReservations(){
        return adminServiceInterface.deleteAllReservations();
    }

    @DeleteMapping("/deleteReservation/{email}")
    public DeleteResponse deleteReservationByCustomerEmail(@PathVariable String email) throws NotFoundException {
        return adminServiceInterface.deleteReservationsByCustomerEmail(email);
    }
    @GetMapping("/availableRooms")
    public List<Room> findAllAvailableRooms(){
        return adminServiceInterface.findAllAvailableRooms();
    }
}

