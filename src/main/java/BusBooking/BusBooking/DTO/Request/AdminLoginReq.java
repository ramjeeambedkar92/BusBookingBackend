package BusBooking.BusBooking.DTO.Request;

import lombok.Data;

@Data
public class AdminLoginReq {
    private String companyEmail;
    private String passwordHash;
}
