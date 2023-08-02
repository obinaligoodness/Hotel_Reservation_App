package africa.semicolon.Hotel_Reservation.models;

import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;
    private boolean status;
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;
    @Column(unique = true)
    private int roomNumber;
    private BigDecimal price;
}
