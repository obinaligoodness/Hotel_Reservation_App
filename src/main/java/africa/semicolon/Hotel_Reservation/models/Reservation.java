package africa.semicolon.Hotel_Reservation.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;
    private int roomNumber;
    private boolean status;
    private Long roomId;
    private BigDecimal price;
}
