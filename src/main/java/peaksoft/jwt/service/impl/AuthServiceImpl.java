package peaksoft.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.jwt.config.jwt.JwtService;
import peaksoft.jwt.dto.request.AuthRequest;
import peaksoft.jwt.dto.response.AuthResponse;
import peaksoft.jwt.dto.response.JwtTokenResponse;
import peaksoft.jwt.dto.request.UserRequest;
import peaksoft.jwt.models.Role;
import peaksoft.jwt.models.User;
import peaksoft.jwt.repo.UserRepo;
import peaksoft.jwt.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse signUp(UserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setRole(Role.USER);

        String encodePassword = passwordEncoder.encode(userRequest.password());
        user.setPassword(encodePassword);

        User savedUser = userRepo.save(user);

        String token = jwtService.createToken(savedUser);

        JwtTokenResponse.builder()
                .accessToken(token)
                .issueDate()
                .build();

        return AuthResponse
                .builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }

    @Override
    public JwtTokenResponse sigIn(AuthRequest authRequest) {
        User user = userRepo.findUserByEmailEqualsIgnoreCase(authRequest.login());

        return JwtTokenResponse.builder().build();

    }

}
