package africa.semicolon.Hotel_Reservation.repositories;

import africa.semicolon.Hotel_Reservation.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findAdminByEmail(String email);
}
