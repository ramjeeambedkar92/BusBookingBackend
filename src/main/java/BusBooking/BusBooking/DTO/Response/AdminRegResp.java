package BusBooking.BusBooking.DTO.Response;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Data
public class AdminRegResp {
    private Integer id;
    private String companyName;
    private String companyEmail;
    private String CompanyPhone;
    private Date CreatedDate;
    private Date updatedDate;
}
