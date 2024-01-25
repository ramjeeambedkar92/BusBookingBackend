package BusBooking.BusBooking;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusBookingApplication.class, args);
	}

	@Bean
	public ModelMapper modalmapper(){
		return new ModelMapper();
	}

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

}
