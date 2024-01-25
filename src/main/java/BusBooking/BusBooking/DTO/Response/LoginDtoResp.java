package BusBooking.BusBooking.DTO.Response;

import lombok.Data;

import java.util.Date;
@Data
public class LoginDtoResp {
    private Integer id;
    private String name;
    private String mobileNumber;
    private String email;
    private String profilePicture;
    private Date createdDate;
    private Date updatedDate;
}
