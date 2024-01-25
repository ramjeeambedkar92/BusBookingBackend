package BusBooking.BusBooking.DTO;

import lombok.Data;

import java.util.List;
@Data
public class PassangerDto {
    private Integer bookingId;
    private Integer scheduleId;
    private List<PassangerList> passangerLists;
}
