package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTO.PassangerDto;
import BusBooking.BusBooking.DTO.PassangerList;

import java.util.List;

public interface PassangerService {
    public PassangerDto addPassangersToBooking (PassangerDto passangerDto);

    public List<PassangerList> GetAllSchedulePassangersList(Integer scheduleId);

    public  List<PassangerList> getPassangersByBookingId(Integer bookingId);



}
