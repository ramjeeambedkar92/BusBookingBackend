package BusBooking.BusBooking.DTO;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class BookedSeats {
    Set<Integer> seatReserved = new HashSet<>();
}
