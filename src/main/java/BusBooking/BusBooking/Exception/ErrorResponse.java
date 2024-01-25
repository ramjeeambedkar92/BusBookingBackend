package BusBooking.BusBooking.Exception;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorResponse {
    private String message;
    private int statusCode;
    private Date date;
}
