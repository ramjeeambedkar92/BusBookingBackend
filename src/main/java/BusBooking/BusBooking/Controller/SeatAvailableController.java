package BusBooking.BusBooking.Controller;

import BusBooking.BusBooking.DTO.BookedSeats;
import BusBooking.BusBooking.DTO.PassangerDto;
import BusBooking.BusBooking.DTO.SeatAvailblleDTO;
import BusBooking.BusBooking.Service.SeatAvailbleService;
import BusBooking.BusBooking.Utils.ResponseGenerater;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bus")
public class SeatAvailableController {
    private SeatAvailbleService seatAvailbleService;

    public SeatAvailableController(SeatAvailbleService seatAvailbleService) {
        this.seatAvailbleService = seatAvailbleService;
    }

    @PostMapping("/checkSeat")
    public ResponseEntity<Object> checkavailiability(@RequestBody PassangerDto passangerDto){
        SeatAvailblleDTO seatAvailblleDTO = seatAvailbleService.checkSeatAvailiable(passangerDto);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Scheduled fetched Successsfully",seatAvailblleDTO);
    }

    @GetMapping("/checkReversed/{id}")
    public ResponseEntity<Object> checkreversed(@PathVariable(name = "id") Integer passangerDto){
        BookedSeats bookedSeats = seatAvailbleService.reservedSeats(passangerDto);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Reserved seats fetched Successsfully",bookedSeats);
    }
}
