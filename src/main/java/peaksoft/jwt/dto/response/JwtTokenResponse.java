package peaksoft.jwt.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record JwtTokenResponse(String accessToken, Instant issueDate) {
}



