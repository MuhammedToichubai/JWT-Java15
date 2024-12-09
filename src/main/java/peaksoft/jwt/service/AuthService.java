package peaksoft.jwt.service;

import peaksoft.jwt.dto.AuthRequest;
import peaksoft.jwt.dto.AuthResponse;
import peaksoft.jwt.dto.JwtToken;
import peaksoft.jwt.dto.UserRequest;

public interface AuthService {
    AuthResponse signUp(UserRequest userRequest);

    JwtToken sigIn(AuthRequest authRequest);
}
