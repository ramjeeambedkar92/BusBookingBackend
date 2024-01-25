package BusBooking.BusBooking.DTO.Request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MobileNoReq {
    @Size(min = 10, max = Integer.MAX_VALUE)
    private String mobile;
}
