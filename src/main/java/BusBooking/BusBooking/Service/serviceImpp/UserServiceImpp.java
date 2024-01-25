package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTO.Request.CreateProfileDtoReq;
import BusBooking.BusBooking.DTO.Request.ProfileUpdate;
import BusBooking.BusBooking.DTO.Response.LoginDtoResp;
import BusBooking.BusBooking.DTO.Response.UserDto;
import BusBooking.BusBooking.Entity.User;
import BusBooking.BusBooking.Exception.DataNotFounException;
import BusBooking.BusBooking.Exception.UserAlreadyExistsException;
import BusBooking.BusBooking.Repository.UserRepository;
import BusBooking.BusBooking.Service.ScheduleService;
import BusBooking.BusBooking.Service.UserService;
import BusBooking.BusBooking.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpp implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpp(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String SendOtp(String mobileNo) {
        User user = userRepository.findByMobileNumber(mobileNo);
        User newUser = new User();
        if (user == null) {
            newUser.setId(GenerateId.BuildId());
            newUser.setMobileNumber(mobileNo);
            userRepository.save(newUser);
            return "User Created Seccessfully with mobile no " + mobileNo;

        } else if (user.getMobileNumber() != null && user.isProfileCreated() == true && user.isOtp() == true) {
            throw new UserAlreadyExistsException("user aliready exists with mobile number " + mobileNo);

        } else {
            return "Otp sent successfully to mobile no " + mobileNo;
        }


    }

    @Override
    public String otpVerifiried(String mobileNo) {
        User user = userRepository.findByMobileNumber(mobileNo);
        if (user.isOtp() != true) {
            user.setOtp(true);
            user.setRole("user");
            userRepository.save(user);
            return "Otp verified successfully";
        } else if(user.isOtp()==true && user.isProfileCreated() == true) {
            return "otp already verified";
        }
        else
            return "otp verifred successfully";


    }

    @Override
    public LoginDtoResp createUser(CreateProfileDtoReq createProfileDtoReq) {
        User user = userRepository.findByMobileNumber(createProfileDtoReq.getMobileNumber());
        String encode = passwordEncoder.encode(createProfileDtoReq.getPasswardHash());
        user.setPasswardHash(encode);
        user.setMobileNumber(createProfileDtoReq.getMobileNumber());
        user.setName(createProfileDtoReq.getName());
        user.setProfileCreated(true);
        user.setEmail(createProfileDtoReq.getEmail());
        user.setProfilePicture(createProfileDtoReq.getProfilePicture());
        return userToLoginDto(userRepository.save(user));
    }

    @Override
    public LoginDtoResp LoginUser(String LoginUserReq) {
        User user = userRepository.findByMobileNumber(LoginUserReq);
        LoginDtoResp LoginDtoResp = userToLoginDto(user);
        return LoginDtoResp;
    }

    @Override
    public LoginDtoResp getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserAlreadyExistsException("User not found with id " + id));
        LoginDtoResp LoginDtoResp = userToLoginDto(user);
        return LoginDtoResp;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByName(username);

    }

    @Override
    public LoginDtoResp updateProfile(ProfileUpdate profileUpdate) {
        User user = userRepository.findById(profileUpdate.getId()).orElseThrow(() -> new DataNotFounException("User not found with id "+profileUpdate.getId()));
        user.setName(profileUpdate.getName());
        user.setEmail(profileUpdate.getEmail());
        User updateUser = userRepository.save(user);
        return userToLoginDto(updateUser);
    }

    private UserDto createProfileDtoReqtoUser(User createProfileDtoReq) {
        return modelMapper.map(createProfileDtoReq, UserDto.class);
    }

    private LoginDtoResp userToLoginDto(User createProfileDtoReq) {
        return modelMapper.map(createProfileDtoReq, LoginDtoResp.class);
    }


}
