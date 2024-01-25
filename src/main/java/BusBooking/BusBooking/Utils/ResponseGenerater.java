package BusBooking.BusBooking.Utils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

public class ResponseGenerater {
    @JsonPropertyOrder({"StatusCode","Message",  "Response"})
    public static ResponseEntity<Object> ResponseBuilder(HttpStatus statusNo, String message, Object response){
        LinkedHashMap<String, Object> backResponse = new LinkedHashMap<>();
        backResponse.put("Message", message);
        backResponse.put("StatusCode", statusNo);
        backResponse.put("Response", response);
        return new ResponseEntity<>(backResponse, statusNo);
    }
}
