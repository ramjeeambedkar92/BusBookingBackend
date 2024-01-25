package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTO.Response.BookingRegResp;
import BusBooking.BusBooking.DTO.BookingDTO;
import BusBooking.BusBooking.Entity.Booking;
import BusBooking.BusBooking.Entity.Schedule;
import BusBooking.BusBooking.Entity.User;
import BusBooking.BusBooking.Exception.DataNotFounException;
import BusBooking.BusBooking.Repository.BookingRepository;
import BusBooking.BusBooking.Repository.ScheduleRepository;
import BusBooking.BusBooking.Repository.UserRepository;
import BusBooking.BusBooking.Service.BookingService;
import BusBooking.BusBooking.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpp implements BookingService {
    private UserRepository userRepository;
    private ModelMapper mapper;
    private BookingRepository bookingRepository;
    private ScheduleRepository scheduleRepository;
    @Autowired
    public BookingServiceImpp(UserRepository userRepository, ModelMapper mapper, BookingRepository bookingRepository, ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.bookingRepository = bookingRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {

        Schedule schedule = scheduleRepository.findById(bookingDTO.getScheduleId())
                .orElseThrow(() -> new DataNotFounException("Schedule not found with id" + bookingDTO.getScheduleId()));
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new DataNotFounException("User not found with id" + bookingDTO.getUserId()));
        Booking booking = boookingDTOToBooking(bookingDTO);
        booking.setId(GenerateId.BuildId());
        booking.setUser(user);
        booking.setSchedule(schedule);

        Booking save = bookingRepository.save(booking);
        return BookingToBookingDTo(save);
    }

    public BookingDTO BookingToBookingDTo(Booking booking)
    {
        return mapper.map(booking,BookingDTO.class);
    }

    public Booking boookingDTOToBooking(BookingDTO bookingDTO)
    {
        return mapper.map(bookingDTO,Booking.class);
    }


    @Override
    public List<BookingRegResp> getAllBookings(Integer userId) {
        List<Booking> userBookings = bookingRepository.findByUserId(userId);

        if(userBookings.size()>0)
        {
            List<BookingRegResp> collect = userBookings.stream().map((list) -> convertBookingToBookingRegResp(list)).collect(Collectors.toList());
            return collect;
        }
        else {
            throw new DataNotFounException("No Booking found with id "+ userId);
        }

    }

    @Override
    public BookingRegResp getBookingById(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new DataNotFounException("boooking not found with id " + bookingId));
        return convertBookingToBookingRegResp(booking);
    }

    @Override
    public BookingRegResp cancaelBookingById(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new DataNotFounException("boooking not found with id " + bookingId));
        bookingRepository.delete(booking);
        Optional<Booking> deleteeduser = bookingRepository.findById(bookingId);
        if(deleteeduser.isEmpty())
        {
            return convertBookingToBookingRegResp(booking);
        }
       return null;
    }

    public BookingRegResp convertBookingToBookingRegResp(Booking booking)
    {
        return mapper.map(booking,BookingRegResp.class);
    }
}
