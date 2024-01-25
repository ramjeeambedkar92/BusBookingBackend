package BusBooking.BusBooking.Utils;

import io.jsonwebtoken.SignatureAlgorithm;

public interface SecurityConstants {
    public static final String JWT_TokenGenerationKEy = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

    public static final String JWT_HEADER = "Authorization";
    public static final Long TOKEN_EXPIRATION_TIME = 1000*60*60*24*28L;

    public static final String USER_ID_CLAIM = "UserId";

    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256; // Choose the appropriate algorithm

}
