package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTO.BookedSeats;
import BusBooking.BusBooking.DTO.PassangerDto;
import BusBooking.BusBooking.DTO.SeatAvailblleDTO;

public interface SeatAvailbleService {
    public SeatAvailblleDTO checkSeatAvailiable(PassangerDto passangerDto);

    public boolean bookSeats(PassangerDto passangerDto);

    public BookedSeats reservedSeats(Integer scheduleId);



}
