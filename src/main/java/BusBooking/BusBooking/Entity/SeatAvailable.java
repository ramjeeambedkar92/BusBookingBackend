package BusBooking.BusBooking.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class SeatAvailable {
@Id
    private Integer id;
    private Integer seatNo;
    @ManyToOne()
    @JoinColumn(name = "Schedule_id")
    @JsonBackReference
    private Schedule schedule;
}
