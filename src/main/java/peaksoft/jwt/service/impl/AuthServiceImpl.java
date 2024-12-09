package peaksoft.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.jwt.config.jwt.JwtService;
import peaksoft.jwt.dto.AuthRequest;
import peaksoft.jwt.dto.AuthResponse;
import peaksoft.jwt.dto.JwtToken;
import peaksoft.jwt.dto.UserRequest;
import peaksoft.jwt.exceptions.NotFoundException;
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
        user.setPassword(passwordEncoder.encode(userRequest.password())); // user123 // a;skdjf;alwkjesflje2oi23948psjdf
        user.setRole(Role.USER);
        User savedUser = userRepo.save(user);
        JwtToken jwtToken = jwtService.generateToken(savedUser);
        return AuthResponse
                .builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .token(jwtToken)
                .build();
    }

    @Override
    public JwtToken sigIn(AuthRequest authRequest) {
        User user = userRepo.findUserByEmailEqualsIgnoreCase(authRequest.login()).orElseThrow(
                () -> new NotFoundException("User with email: " + authRequest.login() + " not found"));

        boolean matches = passwordEncoder.matches(authRequest.password(), user.getPassword());
        if (!matches) throw  new BadCredentialsException("Bad credentials");

        return jwtService.generateToken(user);

    }

}
