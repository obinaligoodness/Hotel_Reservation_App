package africa.semicolon.Hotel_Reservation.services;

import africa.semicolon.Hotel_Reservation.dtos.requests.ReservationRequest;
import africa.semicolon.Hotel_Reservation.dtos.responses.DeleteResponse;
import africa.semicolon.Hotel_Reservation.dtos.responses.RegistrationResponse;
import africa.semicolon.Hotel_Reservation.dtos.responses.ReservationResponse;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.models.Customer;
import africa.semicolon.Hotel_Reservation.models.Reservation;
import africa.semicolon.Hotel_Reservation.repositories.CustomerRepository;
import africa.semicolon.Hotel_Reservation.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationServiceInterface {
    private final ReservationRepository reservationRepository;
//    private final CustomerServiceInterface customerServiceInterface;
    private final CustomerRepository customerRepository;

    @Override
    public ReservationResponse saveReservations(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(reservationRequest.getCustomerId());
        reservation.setFirstName(reservationRequest.getFirstName());
        reservation.setLastName(reservationRequest.getLastName());
        reservation.setEmail(reservationRequest.getEmail());
        reservation.setPassword(reservationRequest.getPassword());
        reservation.setPhoneNumber(reservationRequest.getPhoneNumber());
        reservation.setRoomNumber(reservationRequest.getRoomNumber());
        reservation.setStatus(false);
        reservation.setPrice(reservationRequest.getPrice());
        reservation.setRoomType(reservationRequest.getRoomType());
        reservation.setRoomId(reservationRequest.getRoomId());
        reservationRepository.save(reservation);
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setMessage("Reservations made successfully");
        return reservationResponse;
    }

    @Override
    public List<Reservation> findAllReservations() {
        List<Reservation> allReservations = reservationRepository.findAll();
        return allReservations;
    }

    @Override
    public List<Reservation> findAllReservationsByCustomerEmail(String email) throws NotFoundException {
        Customer foundCustomer = customerRepository.findCustomerByEmail(email);
        List<Reservation> foundReservations = reservationRepository.findReservationByEmail(foundCustomer.getEmail());
        return foundReservations;
    }
//        List<Reservation> allReservations = reservationRepository.findAll();
//        for (Reservation reservation:allReservations){
//            if (reservation.getEmail().equals(foundCustomer.getEmail())){
//
//            }
//
//        }


    @Override
    public DeleteResponse deleteAllReservations() {
        reservationRepository.deleteAll();
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("deleted Successfully");
        return deleteResponse;
    }

    @Override
    public DeleteResponse deleteReservationsByCustomerEmail(String email) throws NotFoundException {
        List<Reservation> foundReservations = findAllReservationsByCustomerEmail(email);
        reservationRepository.deleteAll(foundReservations);
        DeleteResponse deleteResponse =  new DeleteResponse();
        deleteResponse.setMessage("deleted successfully");
        return deleteResponse;
    }
}
