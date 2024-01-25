package BusBooking.BusBooking.Controller;

import BusBooking.BusBooking.DTO.Request.CreateProfileDtoReq;
import BusBooking.BusBooking.DTO.Request.MobileNoReq;
import BusBooking.BusBooking.DTO.Request.ProfileUpdate;
import BusBooking.BusBooking.DTO.Response.LoginDtoResp;
import BusBooking.BusBooking.Entity.User;
import BusBooking.BusBooking.Repository.UserRepository;
import BusBooking.BusBooking.Service.UserService;
import BusBooking.BusBooking.Utils.ResponseGenerater;
import BusBooking.BusBooking.Utils.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Autowired

    public UserController(UserService userService, UserRepository userRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }



    @PostMapping("/public/SendOtp")
    public ResponseEntity<Object> loginViaOtp(@RequestBody MobileNoReq mobileNoReq) {
        userService.SendOtp(mobileNoReq.getMobile());
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED, "Otp Sent Seccussfully to mobile number" + mobileNoReq.getMobile(), null);
    }

    @PostMapping("/public/otpVerify")
    public ResponseEntity<Object> otpVerify(@RequestBody MobileNoReq mobileNoReq) {
        String isOtpVerified = userService.otpVerifiried(mobileNoReq.getMobile());

        if (isOtpVerified.contains("successfully")) {
            // If OTP is verified successfully, generate JWT token
            User user = userRepository.findByMobileNumber(mobileNoReq.getMobile());

            if (user != null) {
                try {
                    String jwtToken = generateJwtToken(user);


                    // Set the JWT token in the response header
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(SecurityConstants.JWT_HEADER, jwtToken);

                    return new ResponseEntity<>(
                            ResponseGenerater.ResponseBuilder(HttpStatus.OK, "OTP verified successfully with mobile number " + mobileNoReq.getMobile(), null),
                            headers,
                            HttpStatus.OK
                    );
                } catch (Exception e) {
                    log.error("Error generating JWT token", e);
                    return new ResponseEntity<>(
                            ResponseGenerater.ResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating JWT token", null),
                            HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
            }
        }

        // If OTP verification fails or user not found
        return new ResponseEntity<>(
                ResponseGenerater.ResponseBuilder(HttpStatus.UNAUTHORIZED, "OTP verification failed or user not found", null),
                HttpStatus.UNAUTHORIZED
        );
    }

    @PostMapping("/private/createProfile")
    public ResponseEntity<Object> createProfile(@RequestBody CreateProfileDtoReq createProfileDtoReq){
        LoginDtoResp user = userService.createUser(createProfileDtoReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Profile Created seccessfully",user);
    }
    @PostMapping("/private/updateProfile")
    public ResponseEntity<Object> updateProofile(@RequestBody ProfileUpdate createProfileDtoReq){
        LoginDtoResp user = userService.updateProfile(createProfileDtoReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Profile Created seccessfully",user);
    }


    @PostMapping("/public/login")
    public ResponseEntity<Object> login(Authentication authentication){

        LoginDtoResp user = userService.LoginUser(authentication.getName());

        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Login seccessfully",user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") Integer id){
        LoginDtoResp userById = userService.getUserById(id);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"user Fetched Successfully",userById);
    }



    private String generateJwtToken(User user) {
        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_TokenGenerationKEy.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setIssuer("pavan")
                .setSubject("jwtToken")
                .claim("username", user.getName())
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }


}
