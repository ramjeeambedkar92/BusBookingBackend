package BusBooking.BusBooking.DTO.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("profilePicture")
    private String profilePicture;

    @JsonProperty("passwordHash")
    private String passwordHash;

    @JsonProperty("email")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date updatedDate;

    // Constructors, getters, and setters
}
