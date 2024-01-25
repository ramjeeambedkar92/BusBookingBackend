package BusBooking.BusBooking.Config;

import BusBooking.BusBooking.Controller.BusAdminController;
import BusBooking.BusBooking.Entity.BusCompanyAdmin;
import BusBooking.BusBooking.Entity.User;
import BusBooking.BusBooking.Repository.BusCompanyAdminRepository;
import BusBooking.BusBooking.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
@Slf4j
public class BookMyBus implements AuthenticationProvider {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private BusCompanyAdminRepository busCompanyAdminRepository;
@Autowired
    public BookMyBus(UserRepository userRepository, PasswordEncoder passwordEncoder, BusCompanyAdminRepository busCompanyAdminRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.busCompanyAdminRepository = busCompanyAdminRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestPath = request.getRequestURI();
        log.info(requestPath);
        if (requestPath.equals("/api/user/public/login")) {
            User user = userRepository.findByMobileNumber(username);
            if (user != null) {
                if (passwordEncoder.matches(pwd, user.getPasswardHash())) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(user.getRole()));
                    return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
                } else {
                    throw new BadCredentialsException("Invalid creditionals");
                }
            } else {
                throw new BadCredentialsException("No user registered with this details!");
            }
            } else if (requestPath.equals("/api/admin/public/login")) {
            BusCompanyAdmin admin = busCompanyAdminRepository.findByCompanyEmail(username);
            if (admin != null) {
                if (passwordEncoder.matches(pwd, admin.getPasswordHash())) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(admin.getRole()));
                    return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
                } else {
                    throw new BadCredentialsException("Invalid creditionals");
                }
            } else {
                throw new BadCredentialsException("No user registered with this details!");
            }
        }


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
