package BusBooking.BusBooking.DTO.Response;

import BusBooking.BusBooking.Entity.BusOperator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BusRegResp {
    private Integer id;
    private String busName;
    private Integer totalSeats;
    private String busType;
    private String seatType;
    private Integer busOperatorId;
}
