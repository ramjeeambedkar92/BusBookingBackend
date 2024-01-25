package BusBooking.BusBooking.DTO;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class SeatAvailblleDTO {
    private Map<Integer,String> seatMessage = new HashMap<>();
}
