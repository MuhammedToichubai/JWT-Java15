package peaksoft.jwt.dto.response;


import lombok.Builder;
import peaksoft.jwt.models.Role;

@Builder
public record AuthResponse(
        Long userId,
        String email,
        Role role,
        JwtTokenResponse token
) {}
