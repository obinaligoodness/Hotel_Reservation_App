package africa.semicolon.Hotel_Reservation.dtos.requests;

import africa.semicolon.Hotel_Reservation.models.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RoomCreationRequest {
    private boolean status;
    private RoomType roomType;
    private int roomNumber;
    private BigDecimal price;
}
