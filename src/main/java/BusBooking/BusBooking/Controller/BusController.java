package BusBooking.BusBooking.Controller;

import BusBooking.BusBooking.DTO.Request.BusOperatorDtoReq;
import BusBooking.BusBooking.DTO.Request.BusRegReq;
import BusBooking.BusBooking.DTO.Response.BusOperatorDtoResp;
import BusBooking.BusBooking.DTO.Response.BusRegResp;
import BusBooking.BusBooking.Service.BusService;
import BusBooking.BusBooking.Utils.ResponseGenerater;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/bus")
public class BusController {
    private BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createBus(@RequestBody BusRegReq busRegReq) {
        BusRegResp bus = busService.createBus(busRegReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED, "Bus created Successfully", bus);
    }

    @GetMapping("/All/{adminId}")
    public ResponseEntity<Object> getAllBuses(@PathVariable(name = "adminId") Integer adminId)
    {
        List<BusRegResp> allBus = busService.getAllBus(adminId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "fetched all Buses", allBus);
    }
    @GetMapping("/{busId}")
    public ResponseEntity<Object> getBusById(@PathVariable(name = "busId") Integer busId) {
        BusRegResp bus = busService.getBusById(busId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "fetched successfully", bus);
    }

    @PutMapping("/{busId}/update")
    public ResponseEntity<Object> updateBusById(@PathVariable(name = "busId") Integer busId, @RequestBody BusRegReq busRegReq) {
        BusRegResp busRegResp = busService.UpdateBus(busId, busRegReq);
        // You might want to return a ResponseEntity with appropriate status and body
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus  Updated Successfully",busRegResp);
    }

    @DeleteMapping("{busId}/delete")
    public ResponseEntity<Object> deleteBusById(@PathVariable(name = "busId")  Integer id)
    {
        BusRegResp busRegResp = busService.DeleteBusByid(id);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Operator Deleted Successfully",busRegResp);

    }
}
