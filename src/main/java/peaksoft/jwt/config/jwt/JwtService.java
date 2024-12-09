package peaksoft.jwt.config.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import peaksoft.jwt.dto.JwtToken;
import peaksoft.jwt.models.User;

public interface JwtService {

    JwtToken generateToken(User user);


    DecodedJWT validateToken(String token);
}
