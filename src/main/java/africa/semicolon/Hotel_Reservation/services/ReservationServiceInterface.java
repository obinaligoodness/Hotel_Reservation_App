package africa.semicolon.Hotel_Reservation.services;

import africa.semicolon.Hotel_Reservation.dtos.requests.RegistrationRequest;
import africa.semicolon.Hotel_Reservation.dtos.requests.ReservationRequest;
import africa.semicolon.Hotel_Reservation.dtos.responses.DeleteResponse;
import africa.semicolon.Hotel_Reservation.dtos.responses.RegistrationResponse;
import africa.semicolon.Hotel_Reservation.dtos.responses.ReservationResponse;
import africa.semicolon.Hotel_Reservation.exceptions.NotFoundException;
import africa.semicolon.Hotel_Reservation.models.Reservation;

import java.util.List;


public interface ReservationServiceInterface {
    ReservationResponse saveReservations(ReservationRequest reservationRequest);
    List<Reservation> findAllReservations();
    List<Reservation> findAllReservationsByCustomerEmail(String Email) throws NotFoundException;
    DeleteResponse  deleteAllReservations();
    DeleteResponse deleteReservationsByCustomerEmail(String Email) throws NotFoundException;
}
