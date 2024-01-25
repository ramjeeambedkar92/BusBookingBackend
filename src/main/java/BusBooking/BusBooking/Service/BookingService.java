package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTO.Response.BookingRegResp;
import BusBooking.BusBooking.DTO.BookingDTO;

import java.util.List;

public interface BookingService {

    public BookingDTO createBooking (BookingDTO BookingDTO);


    public List<BookingRegResp> getAllBookings(Integer userId);
    public BookingRegResp getBookingById(Integer id);
    public BookingRegResp cancaelBookingById(Integer id);
}
