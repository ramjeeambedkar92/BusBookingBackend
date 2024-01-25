package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTO.BookedSeats;
import BusBooking.BusBooking.DTO.PassangerDto;
import BusBooking.BusBooking.DTO.SeatAvailblleDTO;
import BusBooking.BusBooking.Entity.Schedule;
import BusBooking.BusBooking.Entity.SeatAvailable;
import BusBooking.BusBooking.Exception.DataNotFounException;
import BusBooking.BusBooking.Repository.ScheduleRepository;
import BusBooking.BusBooking.Repository.SeatAvailibleRespository;
import BusBooking.BusBooking.Service.SeatAvailbleService;
import BusBooking.BusBooking.Utils.GenerateId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j

public class SeatAvailbleServiceImpp implements SeatAvailbleService {
    private SeatAvailibleRespository seatAvailibleRespository;
    private ScheduleRepository scheduleRepository;
@Autowired
    public SeatAvailbleServiceImpp(SeatAvailibleRespository seatAvailibleRespository, ScheduleRepository scheduleRepository) {
        this.seatAvailibleRespository = seatAvailibleRespository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public SeatAvailblleDTO checkSeatAvailiable(PassangerDto passangerDto) {
        SeatAvailblleDTO seat = new SeatAvailblleDTO();
        passangerDto.getPassangerLists().forEach((passenger) -> {
            SeatAvailable isAvailable = seatAvailibleRespository.findBySeatNoAndScheduleId(passenger.getSeatNumber(), passangerDto.getScheduleId());
            if (isAvailable == null) {
                seat.getSeatMessage().put(passenger.getSeatNumber(), "available");
            } else {
                seat.getSeatMessage().put(passenger.getSeatNumber(), "not-available");
            }
        });

        return seat;
    }

    public SeatAvailbleServiceImpp(SeatAvailibleRespository seatAvailibleRespository) {
        this.seatAvailibleRespository = seatAvailibleRespository;
    }

    @Override
    public boolean bookSeats(PassangerDto passangerDto) {
        AtomicBoolean isBooked = new AtomicBoolean(false); // Initialize isBooked
        Set<Integer> nonBooking  = new HashSet<>();
        Schedule schedule = scheduleRepository.findById(passangerDto.getScheduleId())
                .orElseThrow(() -> new DataNotFounException("Schedule not found with id " + passangerDto.getScheduleId()));

        SeatAvailblleDTO seat = new SeatAvailblleDTO();

        passangerDto.getPassangerLists().forEach((passenger) -> {
            SeatAvailable isAvailable = seatAvailibleRespository.findBySeatNoAndScheduleId(passenger.getSeatNumber(), passangerDto.getScheduleId());
            if (isAvailable == null) {
                SeatAvailable seatAvailable = new SeatAvailable();
                seatAvailable.setId(GenerateId.BuildId()); // Assuming buildId() is a valid method for generating an ID
                seatAvailable.setSeatNo(passenger.getSeatNumber());
                seatAvailable.setSchedule(schedule);
                seatAvailibleRespository.save(seatAvailable);
                seat.getSeatMessage().put(passenger.getSeatNumber(), "Booked");
            } else {
                seat.getSeatMessage().put(passenger.getSeatNumber(), "Already Booked");
                isBooked.set(true);
                nonBooking.add(passenger.getSeatNumber());

            }


        });
        if(!isBooked.get())
        {
            return true;
        }

        else  {
            return false;
        }


    }

    @Override
    public BookedSeats reservedSeats(Integer scheduleId) {
        Set<SeatAvailable> scheduleId1 = seatAvailibleRespository.findByScheduleId(scheduleId);
        BookedSeats bookedSeats = new BookedSeats();
        scheduleId1.forEach((list)->{
            bookedSeats.getSeatReserved().add(list.getSeatNo());
        });
        return bookedSeats;
    }


}
