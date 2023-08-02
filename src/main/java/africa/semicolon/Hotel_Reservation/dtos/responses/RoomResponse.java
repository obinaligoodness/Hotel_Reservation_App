package africa.semicolon.Hotel_Reservation.dtos.responses;

import africa.semicolon.Hotel_Reservation.models.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class RoomResponse {
    private boolean status;
    private RoomType roomType;
    private int roomNumber;
    private BigDecimal price;
}
