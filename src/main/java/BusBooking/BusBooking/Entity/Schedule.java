package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Schedule implements Serializable {
    @Id
    private Integer id;

    private Date departureTime;
    private String origin;
    private String destination;
    private String duration;
    private Integer distance;

    @Column(name = "arrival_time")
    private Date arrivalTime;

    @Column(name = "price")
    private Double price;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Booking> bookings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    @JsonBackReference
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @JsonBackReference
    private BusCompanyAdmin busCompanyAdmin;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Passenger> passenger;

    @OneToMany(mappedBy = "schedule",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SeatAvailable> seatAvailables = new ArrayList<>();
}