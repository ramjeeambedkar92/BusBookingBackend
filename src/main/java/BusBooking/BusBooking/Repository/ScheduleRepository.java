package BusBooking.BusBooking.Repository;

import BusBooking.BusBooking.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query("SELECT e FROM Schedule e " +
            "WHERE e.origin = :origin " +
            "AND e.destination = :destination " +
            "AND FUNCTION('DATE', e.arrivalTime) = :arrivalDate")
    List<Schedule> findByOriginAndDestinationAndArrivalDate(
            @Param("origin") String origin,
            @Param("destination") String destination,
            @Param("arrivalDate") LocalDate arrivalDate
    );

    List<Schedule> findByBusCompanyAdminId(Integer adminId);
}
