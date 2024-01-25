package BusBooking.BusBooking.DTO;

import lombok.Data;

@Data
public class PassangerList {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private Integer seatNumber;

}
