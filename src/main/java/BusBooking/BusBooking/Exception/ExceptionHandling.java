package BusBooking.BusBooking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> alreadyExists(Exception exe){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exe.getMessage());
        errorResponse.setDate(new Date());
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataNotFounException.class)
    public ResponseEntity<ErrorResponse> dataAlreadyFound(Exception exe){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exe.getMessage());
        errorResponse.setDate(new Date());
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataMissMatchException.class)
    public ResponseEntity<ErrorResponse> dataMissMatchException(Exception exe){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exe.getMessage());
        errorResponse.setDate(new Date());
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }


}
