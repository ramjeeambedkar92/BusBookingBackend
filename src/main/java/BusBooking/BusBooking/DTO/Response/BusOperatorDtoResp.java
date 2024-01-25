package BusBooking.BusBooking.DTO.Response;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
@Data
public class BusOperatorDtoResp {
    private Integer id;
    private String operatorName;
    private String operatorEmail;
    private String OperatorMobile;
    private Date createdDate;
    private Date UpdatedDate;

}
