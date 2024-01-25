package BusBooking.BusBooking.DTO.Request;

import lombok.Data;

@Data
public class LoginUserReq {
    private String mobileNo;
    private String passward;
}
