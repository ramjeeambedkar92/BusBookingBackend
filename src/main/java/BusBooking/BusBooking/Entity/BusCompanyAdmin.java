package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class BusCompanyAdmin implements Serializable {
    @Id
    private Integer id;
    private String companyName;
    private String companyEmail;
    private String CompanyPhone;
    private String passwordHash;
    private String role;
    @CreationTimestamp
    private Date CreatedDate;
    @UpdateTimestamp
    private Date updatedDate;

    @OneToMany(mappedBy = "busAdmin",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BusOperator> busOperators = new ArrayList<>();
    @OneToMany(mappedBy = "busCompanyAdmin",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bus> busList = new ArrayList<>();

    @OneToMany(mappedBy = "busCompanyAdmin",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Schedule> schedules = new HashSet<>();
}
