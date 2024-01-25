package BusBooking.BusBooking.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BusOperatorDtoReq {
    private Integer admin_id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 2 , max = 20)
    private String operatorName;
    @NotNull(message = "Email cannot be null")
    private String operatorEmail;
    @NotNull(message = "Email cannot be null")
    private String operatorMobile;
}
