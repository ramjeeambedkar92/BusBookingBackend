package BusBooking.BusBooking.DTO.Response;

import lombok.Data;

@Data
public class UserBasicDto {
    private Long id;
    private String name;
    private String mobileNumber;
    private String profilePicture;
    private String email;
}
