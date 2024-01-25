package BusBooking.BusBooking.DTO.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CreateProfileDtoReq {

    private String name;
    @Size(min = 10)
    private String mobileNumber;
    @Email
    private String email;
    private String profilePicture;
    private String passwardHash;


}
