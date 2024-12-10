package peaksoft.jwt.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.jwt.dto.request.AuthRequest;
import peaksoft.jwt.dto.response.AuthResponse;
import peaksoft.jwt.dto.response.JwtTokenResponse;
import peaksoft.jwt.dto.request.UserRequest;
import peaksoft.jwt.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthAPI {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public AuthResponse signUp(@RequestBody UserRequest userRequest){
       return authService.signUp(userRequest);
    }

    @GetMapping("/sign-in")
    public JwtTokenResponse signIn(@RequestBody AuthRequest authRequest){
        return authService.sigIn(authRequest);
    }


}
