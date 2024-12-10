package peaksoft.jwt.dto.response;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record JwtTokenResponse(String accessToken, ZonedDateTime issuedAt, ZonedDateTime expiresAt) {
}



