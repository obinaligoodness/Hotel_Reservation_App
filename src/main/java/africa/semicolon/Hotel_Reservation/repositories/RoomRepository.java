package africa.semicolon.Hotel_Reservation.repositories;

import africa.semicolon.Hotel_Reservation.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findRoomByRoomNumber(int roomNumber);
    List<Room> findRoomByStatus(boolean status);
}
