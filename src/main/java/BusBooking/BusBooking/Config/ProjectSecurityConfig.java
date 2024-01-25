package BusBooking.BusBooking.Config;

import BusBooking.BusBooking.Config.Filter.JWTTokenGeneratorFilter;
import BusBooking.BusBooking.Config.Filter.JWTTokenValidatorFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class ProjectSecurityConfig {
    private JWTTokenGeneratorFilter jwtTokenGeneratorFilter;

    public ProjectSecurityConfig(JWTTokenGeneratorFilter jwtTokenGeneratorFilter) {
        this.jwtTokenGeneratorFilter = jwtTokenGeneratorFilter;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                                                                               @Override
                                                                               public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                                                                   CorsConfiguration config = new CorsConfiguration();
                                                                                   config.setAllowedOrigins(Collections.singletonList("http://34.227.86.97/"));
                                                                                   config.setAllowedMethods(Collections.singletonList("*"));
                                                                                   config.setAllowCredentials(true);
                                                                                   config.setAllowedHeaders(Collections.singletonList("*"));
                                                                                   config.setExposedHeaders(Arrays.asList("Authorization"));
                                                                                   config.setMaxAge(3600L);
                                                                                   return config;


                                                                               }
                                                                           }
                )).csrf((csrf) -> csrf.disable())
                .addFilterAfter(jwtTokenGeneratorFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/user/public/**","/api/admin/public/**").permitAll()
                        .anyRequest().authenticated())
                 .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://18.208.220.240");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}

