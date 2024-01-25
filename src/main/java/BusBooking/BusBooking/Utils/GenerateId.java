package BusBooking.BusBooking.Utils;

import java.util.Random;

public class GenerateId {
    public static Integer BuildId(){
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return ranNum;
    }
}
