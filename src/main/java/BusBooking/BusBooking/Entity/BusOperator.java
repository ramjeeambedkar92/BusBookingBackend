package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BusOperator implements Serializable {
    @Id
    private Integer id;
    private String operatorName;
    private String operatorEmail;
    private String OperatorMobile;
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    private Date UpdatedDate;
    @OneToMany(mappedBy = "busOperator", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Bus> buses =new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "admin_id")
    private BusCompanyAdmin busAdmin;


}
