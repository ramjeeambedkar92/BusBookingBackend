package BusBooking.BusBooking.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDTO {
    private Integer id;
    private Integer userId;
    private Integer scheduleId;
    @NotNull(message = "Total passengers cannot be empty")
    @Min(value = 1, message = "Total passengers must be greater than 0")
    private Integer totalPassengers;
    @NotNull(message = "Total amount cannot be empty")
    @Min(value = 0, message = "Total amount must be greater than or equal to 0")
            private Double totalAmount;
            private Long paymentMethodId;
            private String status;
}