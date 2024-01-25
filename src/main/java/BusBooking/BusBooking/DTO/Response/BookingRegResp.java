package BusBooking.BusBooking.DTO.Response;

import BusBooking.BusBooking.DTO.ScheduleDTOBusName;
import lombok.Data;

@Data
public class BookingRegResp {
    private Integer id;
    private Integer totalPassengers;
    private Double totalAmount;
    private String status;
    private ScheduleDTOBusName schedule;


}
