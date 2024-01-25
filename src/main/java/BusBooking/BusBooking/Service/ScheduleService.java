package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTO.Request.BusSearchReqDto;
import BusBooking.BusBooking.DTO.Request.ScheduleRegReq;
import BusBooking.BusBooking.DTO.ScheduleDTo;

import java.util.Set;

public interface ScheduleService {
    public ScheduleDTo createSchedule(ScheduleRegReq scheduleRegReq);
    public ScheduleDTo updatedSchedule(Integer sheduleId , ScheduleRegReq scheduleRegReq);
    public Set<ScheduleDTo> getAllSchedule(Integer adminId);
    public ScheduleDTo getSchudleById(Integer sheduleId);
    public ScheduleDTo deleteSchudule(Integer sheduleId);
    
    public Set<ScheduleDTo> searchBus(BusSearchReqDto busSearchReqDto);

}
