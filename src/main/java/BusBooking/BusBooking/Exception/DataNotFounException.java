package BusBooking.BusBooking.Exception;

public class DataNotFounException extends RuntimeException{
    public DataNotFounException(String message) {
        super(message);
    }

    public DataNotFounException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFounException(Throwable cause) {
        super(cause);
    }
}
