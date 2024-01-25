package BusBooking.BusBooking.Exception;

public class DataMissMatchException extends RuntimeException{
    public DataMissMatchException(String message) {
        super(message);
    }

    public DataMissMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataMissMatchException(Throwable cause) {
        super(cause);
    }
}
