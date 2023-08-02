package africa.semicolon.Hotel_Reservation.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}
