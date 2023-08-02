package africa.semicolon.Hotel_Reservation.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRoomRequest {
    private int roomNumber;
    private String email;
}
