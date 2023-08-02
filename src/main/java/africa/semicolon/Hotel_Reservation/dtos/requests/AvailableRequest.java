package africa.semicolon.Hotel_Reservation.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableRequest {
    private boolean status;
    private int roomNumber;
}
