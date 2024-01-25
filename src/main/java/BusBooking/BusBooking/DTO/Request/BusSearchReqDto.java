package BusBooking.BusBooking.DTO.Request;

import lombok.Data;

import java.util.Date;
@Data
public class BusSearchReqDto {
    private Date arrivalTime;
    private String origin;
    private String destination;
}
