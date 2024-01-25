package BusBooking.BusBooking.Repository;

import BusBooking.BusBooking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByMobileNumber(String mobileNo);
    public User findByName(String name);
}
