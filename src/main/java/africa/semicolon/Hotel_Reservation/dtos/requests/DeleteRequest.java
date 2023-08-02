package africa.semicolon.Hotel_Reservation.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteRequest {
    private String email;
    private String password;
}
