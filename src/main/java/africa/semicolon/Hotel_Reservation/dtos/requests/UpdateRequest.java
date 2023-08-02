package africa.semicolon.Hotel_Reservation.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}
