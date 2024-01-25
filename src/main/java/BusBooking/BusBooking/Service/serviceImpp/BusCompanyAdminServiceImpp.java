package BusBooking.BusBooking.Service.serviceImpp;

import BusBooking.BusBooking.DTO.Request.AdminLoginReq;
import BusBooking.BusBooking.DTO.Request.AdminRegReq;
import BusBooking.BusBooking.DTO.Response.AdminRegResp;
import BusBooking.BusBooking.Entity.BusCompanyAdmin;
import BusBooking.BusBooking.Repository.BusCompanyAdminRepository;
import BusBooking.BusBooking.Repository.BusOperatorRepository;
import BusBooking.BusBooking.Service.BusCompanyAdminService;
import BusBooking.BusBooking.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BusCompanyAdminServiceImpp implements BusCompanyAdminService {
    private BusCompanyAdminRepository busCompanyAdminRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    @Autowired

    public BusCompanyAdminServiceImpp(BusCompanyAdminRepository busCompanyAdminRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.busCompanyAdminRepository = busCompanyAdminRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AdminRegResp createAdmin(AdminRegReq adminRegReq) {
        BusCompanyAdmin busCompanyAdmin = new BusCompanyAdmin();
        busCompanyAdmin.setId(GenerateId.BuildId());
        busCompanyAdmin.setRole("Admin");
        busCompanyAdmin.setCompanyName(adminRegReq.getCompanyName());
        busCompanyAdmin.setCompanyPhone(adminRegReq.getCompanyPhone());
        busCompanyAdmin.setCompanyEmail(adminRegReq.getCompanyEmail());
        String passwordHash = adminRegReq.getPasswordHash();
        busCompanyAdmin.setPasswordHash(passwordEncoder.encode(passwordHash));
        BusCompanyAdmin savesAdminDetails = busCompanyAdminRepository.save(busCompanyAdmin);
        return convertBusComapyAdminToADminResp(savesAdminDetails);
    }

    @Override
    public AdminRegResp LoginAdmin(String email) {
        BusCompanyAdmin admin = busCompanyAdminRepository.findByCompanyEmail(email);
        return convertBusComapyAdminToADminResp(admin);
    }

    public AdminRegResp convertBusComapyAdminToADminResp(BusCompanyAdmin busCompanyAdmin) {
        return modelMapper.map(busCompanyAdmin, AdminRegResp
                .class);
    }


}
