package BusBooking.BusBooking.DTO.Request;

import BusBooking.BusBooking.Entity.Schedule;
import BusBooking.BusBooking.Entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
@Data
public class BookingRegReq {

    private Integer totalPassengers;
    private Double totalAmount;
    private String status;
    private Integer scheduleId;
    private Integer userid;

}
