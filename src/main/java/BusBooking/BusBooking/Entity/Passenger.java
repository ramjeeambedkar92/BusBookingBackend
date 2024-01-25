package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "passengers")
@Setter
@Getter
@NoArgsConstructor
public class Passenger implements Serializable {
    @Id
    private Integer id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "seat_number")
    private Integer seatNumber;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    @JsonBackReference
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @JsonBackReference
    private Schedule schedule;
}
