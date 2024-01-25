package BusBooking.BusBooking.Repository;

import BusBooking.BusBooking.Entity.BusCompanyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusCompanyAdminRepository extends JpaRepository<BusCompanyAdmin,Integer> {
    public BusCompanyAdmin findByCompanyEmail(String email);
}
