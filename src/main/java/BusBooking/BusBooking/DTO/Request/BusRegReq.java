package BusBooking.BusBooking.DTO.Request;

import BusBooking.BusBooking.Entity.BusCompanyAdmin;
import BusBooking.BusBooking.Entity.BusOperator;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BusRegReq {
    private String busName;
    private Integer totalSeats;
    private String busType;
    private Integer busOperator;
    private Integer adminID;
        private String seatType;
}
