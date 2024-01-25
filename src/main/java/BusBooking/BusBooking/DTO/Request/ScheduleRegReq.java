package BusBooking.BusBooking.DTO.Request;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleRegReq {
    private Date arrivalTime;
    private Date departureTime;
    private Double price;
    private String duration;
    private String origin;
    private String destination;
    private Integer distance;
    private Integer busId;
    private Integer adminId;


}
