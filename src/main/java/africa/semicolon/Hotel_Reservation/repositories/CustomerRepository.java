package africa.semicolon.Hotel_Reservation.repositories;

import africa.semicolon.Hotel_Reservation.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByEmail(String email);
}
