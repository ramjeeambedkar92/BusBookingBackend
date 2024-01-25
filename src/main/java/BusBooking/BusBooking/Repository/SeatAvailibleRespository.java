package BusBooking.BusBooking.Repository;

import BusBooking.BusBooking.Entity.SeatAvailable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SeatAvailibleRespository extends JpaRepository<SeatAvailable,Integer> {

    public SeatAvailable findBySeatNoAndScheduleId(Integer seatNo,Integer scheduleId);
    public Set<SeatAvailable> findByScheduleId(Integer scheduleId);
}
