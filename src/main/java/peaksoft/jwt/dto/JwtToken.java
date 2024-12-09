package peaksoft.jwt.dto;

import java.time.Instant;

public record JwtToken(String accessToken, String refreshToken, Instant issueDate) {
}



