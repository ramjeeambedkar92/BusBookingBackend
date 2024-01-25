package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTO.Request.AdminLoginReq;
import BusBooking.BusBooking.DTO.Request.AdminRegReq;
import BusBooking.BusBooking.DTO.Response.AdminRegResp;

public interface BusCompanyAdminService {
    public AdminRegResp createAdmin(AdminRegReq adminRegReq);
    public AdminRegResp LoginAdmin(String email);

}
