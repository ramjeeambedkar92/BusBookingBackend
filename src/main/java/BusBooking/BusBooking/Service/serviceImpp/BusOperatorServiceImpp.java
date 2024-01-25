package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTO.Request.BusOperatorDtoReq;
import BusBooking.BusBooking.DTO.Response.BusOperatorDtoResp;
import BusBooking.BusBooking.Entity.BusCompanyAdmin;
import BusBooking.BusBooking.Entity.BusOperator;
import BusBooking.BusBooking.Exception.DataMissMatchException;
import BusBooking.BusBooking.Exception.DataNotFounException;
import BusBooking.BusBooking.Repository.BusCompanyAdminRepository;
import BusBooking.BusBooking.Repository.BusOperatorRepository;
import BusBooking.BusBooking.Service.BusOperatorService;
import BusBooking.BusBooking.Utils.GenerateId;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class BusOperatorServiceImpp implements BusOperatorService {
    private final BusOperatorRepository busOperatorRepository;
    private final ModelMapper modelMapper;
    private final BusCompanyAdminRepository busCompanyAdminRepository;

    @Autowired
    public BusOperatorServiceImpp(BusOperatorRepository busOperatorRepository, ModelMapper modelMapper, BusCompanyAdminRepository busCompanyAdminRepository) {
        this.busOperatorRepository = busOperatorRepository;
        this.modelMapper = modelMapper;
        this.busCompanyAdminRepository = busCompanyAdminRepository;
    }




    @Override
    public BusOperatorDtoResp createBusOperator(BusOperatorDtoReq BusOperatorDtoReq) {
        BusOperator busOperator = new BusOperator();
        BusCompanyAdmin busCompanyAdmin = busCompanyAdminRepository.findById(BusOperatorDtoReq.getAdmin_id()).orElseThrow(() -> new DataNotFounException("Bus Admin not founf with id " + BusOperatorDtoReq.getAdmin_id()));
        busOperator.setId(GenerateId.BuildId());
        busOperator.setBusAdmin(busCompanyAdmin);
        busOperator.setOperatorEmail(BusOperatorDtoReq.getOperatorEmail());
        busOperator.setOperatorMobile(BusOperatorDtoReq.getOperatorMobile());
        busOperator.setOperatorName(BusOperatorDtoReq.getOperatorName());
        BusOperator BusOperatorSavedResponse = busOperatorRepository.save(busOperator);
        return BusOperatorToBusOperatorDtoResp(BusOperatorSavedResponse);
    }

    @Override
    public BusOperatorDtoResp UpdateBusOperator(Integer busOperatorId, BusOperatorDtoReq BusOperatorDtoReq) {
        BusOperator busOperator = busOperatorRepository.findById(busOperatorId).orElseThrow(() -> new DataNotFounException("Bus Operator not found with id " + busOperatorId));
        log.info(String.valueOf(busOperator.getBusAdmin().getId()));
        if (busOperator.getBusAdmin().getId().equals(BusOperatorDtoReq.getAdmin_id())) {
            busOperator.setOperatorEmail(BusOperatorDtoReq.getOperatorEmail());
            busOperator.setOperatorName(BusOperatorDtoReq.getOperatorName());
            busOperator.setOperatorMobile(BusOperatorDtoReq.getOperatorMobile());
            BusOperator UodatedBusOperator = busOperatorRepository.save(busOperator);
            return BusOperatorToBusOperatorDtoResp(UodatedBusOperator);
        } else {
            throw new DataMissMatchException("admin id or Bus Operator id is wrong");
        }

    }

    @Override
    public List<BusOperatorDtoResp> getAllBusOperators(Integer id) {
        List<BusOperator> byBusAdminId = busOperatorRepository.findByBusAdminId(id);
        return byBusAdminId.stream().map((list) -> BusOperatorToBusOperatorDtoResp(list)).collect(Collectors.toList());
    }

    @Override
    public BusOperatorDtoResp getBusOperatorsById(Integer id) {
        BusOperator busOperator = busOperatorRepository.findById(id).orElseThrow(() -> new DataNotFounException("Bus Operator not found with id " + id));
        return BusOperatorToBusOperatorDtoResp(busOperator);
    }

    @Override
    public BusOperatorDtoResp DeleteBusOperatorByid(Integer id) {
        BusOperator busOperator = busOperatorRepository.findById(id).orElseThrow(() -> new DataNotFounException("Bus Operator not found with id " + id));
        busOperatorRepository.delete(busOperator);
        Optional<BusOperator> deletedOperator = busOperatorRepository.findById(id);
        if (deletedOperator.isEmpty()) {
            return BusOperatorToBusOperatorDtoResp(busOperator);
        }
        return null;
    }

    public BusOperator BusOperatorDtoReqToBusOperator(BusOperatorDtoReq BusOperatorDtoReq) {
        return modelMapper.map(BusOperatorDtoReq, BusOperator.class);
    }

    public BusOperatorDtoResp BusOperatorToBusOperatorDtoResp(BusOperator busOperator) {
        return modelMapper.map(busOperator, BusOperatorDtoResp.class);
    }
}
