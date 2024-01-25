package BusBooking.BusBooking.DTO.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileUpdate {
    private Integer id;

    private String name;

    private String email;

}
