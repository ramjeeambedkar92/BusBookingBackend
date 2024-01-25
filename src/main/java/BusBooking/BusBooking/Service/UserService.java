package BusBooking.BusBooking.Service;

import BusBooking.BusBooking.DTO.Request.CreateProfileDtoReq;
import BusBooking.BusBooking.DTO.Request.ProfileUpdate;
import BusBooking.BusBooking.DTO.Response.LoginDtoResp;
import BusBooking.BusBooking.Entity.User;

public interface UserService {
    public String SendOtp(String mobileNo);
    public String otpVerifiried(String mobileNo);
    public LoginDtoResp createUser(CreateProfileDtoReq createProfileDtoReq);
    public LoginDtoResp LoginUser(String mobileNo);

    public LoginDtoResp getUserById(Integer id);

    public User findUserByUsername(String username);

    public LoginDtoResp updateProfile(ProfileUpdate profileUpdate);






}
