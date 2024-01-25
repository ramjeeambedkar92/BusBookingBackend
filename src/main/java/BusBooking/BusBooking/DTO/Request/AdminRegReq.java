package BusBooking.BusBooking.DTO.Request;

import lombok.Data;

@Data
public class AdminRegReq {
    private String companyName;
    private String companyEmail;
    private String CompanyPhone;
    private String passwordHash;
}
