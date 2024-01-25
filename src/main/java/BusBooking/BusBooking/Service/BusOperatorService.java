package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTO.Request.BusOperatorDtoReq;
import BusBooking.BusBooking.DTO.Response.BusOperatorDtoResp;

import java.util.List;

public interface BusOperatorService {

    public BusOperatorDtoResp createBusOperator(BusOperatorDtoReq BusOperatorDtoReq);

    public BusOperatorDtoResp UpdateBusOperator(Integer busOperatorId , BusOperatorDtoReq BusOperatorDtoReq);

    public List<BusOperatorDtoResp> getAllBusOperators(Integer id);

    public BusOperatorDtoResp getBusOperatorsById(Integer id);

    public BusOperatorDtoResp DeleteBusOperatorByid(Integer id);
}
