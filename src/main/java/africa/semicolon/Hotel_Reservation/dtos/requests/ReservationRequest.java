package africa.semicolon.Hotel_Reservation.dtos.requests;

import africa.semicolon.Hotel_Reservation.models.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ReservationRequest {
    private Long id;
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private RoomType roomType;
    private int roomNumber;
    private boolean status;
    private Long roomId;
    private BigDecimal price;
}
