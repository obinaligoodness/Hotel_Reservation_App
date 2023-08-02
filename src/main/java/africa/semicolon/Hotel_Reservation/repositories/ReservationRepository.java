package africa.semicolon.Hotel_Reservation.repositories;

import africa.semicolon.Hotel_Reservation.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findReservationByEmail(String email);
}
