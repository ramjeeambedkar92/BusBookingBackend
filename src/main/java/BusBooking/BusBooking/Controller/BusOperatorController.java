package BusBooking.BusBooking.Controller;

import BusBooking.BusBooking.DTO.Request.BusOperatorDtoReq;
import BusBooking.BusBooking.DTO.Response.BusOperatorDtoResp;
import BusBooking.BusBooking.Service.BusOperatorService;
import BusBooking.BusBooking.Utils.ResponseGenerater;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bus/busOperator")
public class BusOperatorController {
    private BusOperatorService busOperatorService;

    public BusOperatorController(BusOperatorService busOperatorService) {
        this.busOperatorService = busOperatorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> CreateBusOperator(@RequestBody BusOperatorDtoReq busOperatorDtoReq) {
        BusOperatorDtoResp busOperator = busOperatorService.createBusOperator(busOperatorDtoReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED, "Bus Operator Created Successfully", busOperator);
    }

    @GetMapping("/All/{adminid}")
    public ResponseEntity<Object> getAllOperators(@PathVariable(name = "adminid") Integer adminId) {
        List<BusOperatorDtoResp> allBusOperators = busOperatorService.getAllBusOperators(adminId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "Fetched All Bus Operators", allBusOperators);

    }

    @GetMapping("/{operatorId}")
    public ResponseEntity<Object> getBusOperatorById(@PathVariable(name = "operatorId") Integer OperatorId) {
        BusOperatorDtoResp busOperator = busOperatorService.getBusOperatorsById(OperatorId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "Fetched All Bus Operators", busOperator);
    }

    @PutMapping("/{operatorId}/update")
    public ResponseEntity<Object> updateBusOperatorById(@PathVariable(name = "operatorId") Integer busOperatorId, @RequestBody BusOperatorDtoReq busOperatorDtoReq) {
        BusOperatorDtoResp busOperator = busOperatorService.UpdateBusOperator(busOperatorId, busOperatorDtoReq);
        // You might want to return a ResponseEntity with appropriate status and body
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Operator updated Successfully",busOperator);
    }

    @DeleteMapping("{operatorId}/delete")
    public ResponseEntity<Object> deleteBusOperatorById(@PathVariable(name = "operatorId")  Integer id)
    {
        BusOperatorDtoResp busOperator = busOperatorService.DeleteBusOperatorByid(id);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Operator Deleted Successfully",busOperator);

    }




}
