package peaksoft.jwt.service;

import peaksoft.jwt.dto.request.AuthRequest;
import peaksoft.jwt.dto.response.AuthResponse;
import peaksoft.jwt.dto.response.JwtTokenResponse;
import peaksoft.jwt.dto.request.UserRequest;

public interface AuthService {
    AuthResponse signUp(UserRequest userRequest);

    JwtTokenResponse sigIn(AuthRequest authRequest);
}
