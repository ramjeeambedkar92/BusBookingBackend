package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTO.PassangerDto;
import BusBooking.BusBooking.DTO.PassangerList;
import BusBooking.BusBooking.Entity.Booking;
import BusBooking.BusBooking.Entity.Passenger;
import BusBooking.BusBooking.Entity.Schedule;
import BusBooking.BusBooking.Exception.DataNotFounException;
import BusBooking.BusBooking.Repository.BookingRepository;
import BusBooking.BusBooking.Repository.PassengerRepository;
import BusBooking.BusBooking.Repository.ScheduleRepository;
import BusBooking.BusBooking.Service.PassangerService;
import BusBooking.BusBooking.Service.SeatAvailbleService;
import BusBooking.BusBooking.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassangersServiceImpp implements PassangerService {
    private PassengerRepository passengerRepository;
    private ScheduleRepository scheduleRepository;
    private BookingRepository bookingRepository;
    private ModelMapper modelMapper;
    private SeatAvailbleService seatAvailable;
    @Autowired
    public PassangersServiceImpp(PassengerRepository passengerRepository, ScheduleRepository scheduleRepository, BookingRepository bookingRepository, ModelMapper modelMapper, SeatAvailbleService seatAvailable) {
        this.passengerRepository = passengerRepository;
        this.scheduleRepository = scheduleRepository;
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
        this.seatAvailable = seatAvailable;
    }




    @Override
    public PassangerDto addPassangersToBooking(PassangerDto passangerDto) {
       boolean notReserved=seatAvailable.bookSeats(passangerDto);
        List<PassangerDto> passangerDtos = new ArrayList<>();
        if(notReserved)
        {
            Schedule schedule = scheduleRepository.findById(passangerDto.getScheduleId()).orElseThrow(() ->
                    new DataNotFounException("Schedule not found with id" + passangerDto.getScheduleId()));

            Booking booking = bookingRepository.findById(passangerDto.getBookingId()).orElseThrow(() ->
                    new DataNotFounException("Booking not found with id " + passangerDto.getBookingId()));
            booking.setStatus("Booked");

            List<PassangerList> collect = passangerDto.getPassangerLists().stream().map((list) ->
            {
                Passenger passenger = convertPassangerDtoTOPassanget(list);
                passenger.setId(GenerateId.BuildId());
                passenger.setSchedule(schedule);
                passenger.setBooking(booking);


                Passenger objectSaved = passengerRepository.save(passenger);
                return convertPAssangetTopassangerDTo(objectSaved);
            }).collect(Collectors.toList());

            PassangerDto passangerDto1 = new PassangerDto();
            passangerDto1.setPassangerLists(collect);
            passangerDto1.setBookingId(passangerDto.getScheduleId());
            passangerDto1.setScheduleId(passangerDto.getScheduleId());
            return passangerDto1;
        }
        else
            throw new DataNotFounException("Please restart the booking");

    }

    @Override
    public List<PassangerList> GetAllSchedulePassangersList(Integer scheduleId) {
        List<Passenger> PassengerList = passengerRepository.findByScheduleId(scheduleId);
        return PassengerList.stream().map((list)-> convertPAssangetTopassangerDTo(list)).collect(Collectors.toList());


    }

    @Override
    public List<PassangerList> getPassangersByBookingId(Integer bookingId) {
        List<Passenger> byBookingId = passengerRepository.findByBookingId(bookingId);
return    byBookingId.stream().map((list)->convertPAssangetTopassangerDTo(list)).collect(Collectors.toList());

    }

    public Passenger convertPassangerDtoTOPassanget(PassangerList passangerList)
    {
        return  modelMapper.map(passangerList,Passenger.class);

    }

    public PassangerList convertPAssangetTopassangerDTo(Passenger passenger)
    {
        return  modelMapper.map(passenger,PassangerList.class);

    }
}
