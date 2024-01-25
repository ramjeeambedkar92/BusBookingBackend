package BusBooking.BusBooking.Config.Filter;

import BusBooking.BusBooking.Entity.BusCompanyAdmin;
import BusBooking.BusBooking.Entity.User;
import BusBooking.BusBooking.Repository.BusCompanyAdminRepository;
import BusBooking.BusBooking.Repository.UserRepository;
import BusBooking.BusBooking.Service.UserService;
import BusBooking.BusBooking.Utils.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Slf4j
@Component

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {
    private UserRepository userRepository;
    private BusCompanyAdminRepository busCompanyAdminRepository;
@Autowired

    public JWTTokenGeneratorFilter(UserRepository userRepository, BusCompanyAdminRepository busCompanyAdminRepository) {
        this.userRepository = userRepository;
        this.busCompanyAdminRepository = busCompanyAdminRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(request.getServletPath().equals("/api/admin/public/create")) {
            User user = userRepository.findByMobileNumber(authentication.getName());
            log.info(user.toString());
            if (null != user) {
                SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_TokenGenerationKEy.getBytes(StandardCharsets.UTF_8));
                String jwt = Jwts.builder().setIssuer("pavan Bank").setSubject("JWT Token")
                        .claim("username", user.getName())
                        .claim("id", user.getId())
                        .claim("authorities", user.getRole())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date((new Date()).getTime() + SecurityConstants.TOKEN_EXPIRATION_TIME))
                        .signWith(key).compact();
                response.setHeader(SecurityConstants.JWT_HEADER, jwt);
            }
        } else if (request.getServletPath().equals("/api/admin/public/login")) {
            BusCompanyAdmin admin = busCompanyAdminRepository.findByCompanyEmail(authentication.getName());
            if (null != admin) {
                SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_TokenGenerationKEy.getBytes(StandardCharsets.UTF_8));
                String jwt = Jwts.builder().setIssuer("pavan Bank").setSubject("JWT Token")
                        .claim("username", admin.getCompanyName())
                        .claim("id", admin.getId())
                        .claim("authorities", admin.getRole())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date((new Date()).getTime() + SecurityConstants.TOKEN_EXPIRATION_TIME))
                        .signWith(key).compact();
                response.setHeader(SecurityConstants.JWT_HEADER, jwt);
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !("/api/user/public/create".equals(request.getServletPath()) || "/api/admin/public/login".equals(request.getServletPath()));


    }

}
