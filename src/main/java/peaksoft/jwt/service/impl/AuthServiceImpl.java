package peaksoft.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.jwt.config.jwt.JwtService;
import peaksoft.jwt.dto.request.AuthRequest;
import peaksoft.jwt.dto.response.AuthResponse;
import peaksoft.jwt.dto.response.JwtTokenResponse;
import peaksoft.jwt.dto.request.UserRequest;
import peaksoft.jwt.exceptions.NotFoundException;
import peaksoft.jwt.exceptions.PasswordInvalidException;
import peaksoft.jwt.models.Role;
import peaksoft.jwt.models.User;
import peaksoft.jwt.repo.UserRepo;
import peaksoft.jwt.service.AuthService;

import javax.security.auth.login.CredentialException;
import java.rmi.NotBoundException;

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
        JwtTokenResponse jwtTokenResponse = jwtService.createToken(savedUser);

        return AuthResponse
                .builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .token(jwtTokenResponse)
                .build();
    }

    @Override
    public JwtTokenResponse sigIn(AuthRequest authRequest) {
        User user = userRepo.findUserByEmailEqualsIgnoreCase(authRequest.login());

        if (user == null) throw new NotFoundException("User with email "+authRequest.login()+" not found");

        boolean matches = passwordEncoder.matches(authRequest.password(), user.getPassword());
        if (!matches) throw new PasswordInvalidException("Password incorrect!");

        return jwtService.createToken(user);

    }

}
