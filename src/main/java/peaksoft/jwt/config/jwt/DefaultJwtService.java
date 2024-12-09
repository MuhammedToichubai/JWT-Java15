package peaksoft.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.jwt.dto.JwtToken;
import peaksoft.jwt.models.User;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DefaultJwtService implements JwtService {
    private final JwtProperties properties;


    @Override
    public JwtToken generateToken(User user) {
        Instant issueDate = Instant.now();
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", user.getId());
        builder.withClaim("name", user.getName());
        builder.withClaim("email", user.getEmail());
        builder.withClaim("role", user.getRole().name());
        builder.withIssuedAt(issueDate);

        String accessToken = builder.withExpiresAt(issueDate.plusSeconds(properties.getAccessTokenValidityInSeconds()))
                .withClaim("token_type", "access_token")
                .sign(properties.getAlgorithm());

        String refreshToken = builder.withExpiresAt(issueDate.plusSeconds(properties.getRefreshTokenValidityInSeconds()))
                .withClaim("refresh_token", "refresh_token")
                .sign(properties.getAlgorithm());

        return new JwtToken(accessToken, refreshToken, issueDate);
    }

    @Override
    public DecodedJWT validateToken(String token) {
        JWTVerifier verifier = JWT.require(properties.getAlgorithm()).build();
        return verifier.verify(token);
    }


}
