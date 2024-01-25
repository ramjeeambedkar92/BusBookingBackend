package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTO.Request.BusRegReq;
import BusBooking.BusBooking.DTO.Response.BusOperatorDtoResp;
import BusBooking.BusBooking.DTO.Response.BusRegResp;
import BusBooking.BusBooking.Entity.Bus;
import BusBooking.BusBooking.Entity.BusCompanyAdmin;
import BusBooking.BusBooking.Entity.BusOperator;
import BusBooking.BusBooking.Exception.DataMissMatchException;
import BusBooking.BusBooking.Exception.DataNotFounException;
import BusBooking.BusBooking.Repository.BusCompanyAdminRepository;
import BusBooking.BusBooking.Repository.BusOperatorRepository;
import BusBooking.BusBooking.Repository.BusRepository;
import BusBooking.BusBooking.Service.BusService;
import BusBooking.BusBooking.Utils.GenerateId;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
@Transactional
public class BusServiceImpp implements BusService {
    private final ModelMapper modelMapper;
    private final BusRepository busRepository;
    private final BusCompanyAdminRepository busCompanyAdminRepository;
    private final BusOperatorRepository busOperatorRepository;

    @Autowired
    public BusServiceImpp(ModelMapper modelMapper, BusRepository busRepository, BusCompanyAdminRepository busCompanyAdminRepository,BusOperatorRepository busOperatorRepository) {
        this.modelMapper = modelMapper;
        this.busRepository = busRepository;
        this.busCompanyAdminRepository = busCompanyAdminRepository;
        this.busOperatorRepository = busOperatorRepository;
    }

    @Override
    public BusRegResp createBus(BusRegReq busRegReq) {
        Bus bus = new Bus();
        bus.setId(GenerateId.BuildId());
        bus.setBusType(busRegReq.getBusType());
        bus.setSeatType(busRegReq.getSeatType());
        bus.setBusName(busRegReq.getBusName());
        bus.setTotalSeats(busRegReq.getTotalSeats());
        BusCompanyAdmin busCompanyAdmin = busCompanyAdminRepository.findById(busRegReq.getAdminID()).orElseThrow(() -> new DataNotFounException("admin not found with id " + busRegReq.getAdminID()));
        bus.setBusCompanyAdmin(busCompanyAdmin);
        BusOperator busOperator = busOperatorRepository.findById(busRegReq.getBusOperator()).orElseThrow(() -> new DataNotFounException("Bus operator not found with id" + busRegReq.getBusOperator()));
        log.info(busOperator.toString());
        bus.setBusOperator(busOperator);
        Bus savedBus = busRepository.save(bus);
        return BusTOBusRegResp(savedBus);
    }

    @Override
    public BusRegResp UpdateBus(Integer BusId, BusRegReq busRegReq) {
        Bus bus = busRepository.findById(BusId).orElseThrow(() -> new DataNotFounException("Bus not found with id " + BusId));
        boolean isBusAdminTrue = bus.getBusCompanyAdmin().getId().equals(busRegReq.getAdminID());
        if(isBusAdminTrue){
            BusOperator busOperator = busOperatorRepository.findById(busRegReq.getBusOperator()).orElseThrow(() -> new DataNotFounException("Bus operator not fpund with id " + busRegReq.getBusOperator()));
            bus.setBusType(busRegReq.getBusType());
            bus.setBusName(busRegReq.getBusName());
            bus.setSeatType(busRegReq.getSeatType());
            bus.setBusOperator(busOperator);
            bus.setTotalSeats(busRegReq.getTotalSeats());
            Bus updatedBus = busRepository.save(bus);
            return BusTOBusRegResp(updatedBus);
        }
        else{
            throw new DataMissMatchException("Wrong Admin id "+busRegReq.getAdminID());
        }
    }

    @Override
    public List<BusRegResp> getAllBus(Integer adminId) {
        List<Bus> busList = busRepository.findByBusCompanyAdminId(adminId);
        List<BusRegResp> collect = busList.stream().map((list) -> BusTOBusRegResp(list)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public BusRegResp getBusById(Integer busid) {
        Bus bus = busRepository.findById(busid).orElseThrow(() -> new DataNotFounException("Bus operator not found with id" + busid));
        return BusTOBusRegResp(bus);
    }

    @Override
    public BusRegResp DeleteBusByid(Integer id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new DataNotFounException("Bus Operator not found with id " + id));
        busRepository.delete(bus);
        Optional<Bus> optionalBus = busRepository.findById(id);
        if (optionalBus.isEmpty()) {
            return BusTOBusRegResp(bus);
        }
        else
        return null;
    }

    public BusRegResp BusTOBusRegResp(Bus bus)
    {
        return modelMapper.map(bus,BusRegResp.class);
    }
}
