package peaksoft.jwt.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.jwt.dto.response.UserProfileResponse;
import peaksoft.jwt.models.User;
import peaksoft.jwt.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPI {
    private final UserService userService;


//  @PreAuthorize("hasAuthority('ADMIN')")
    @Secured("ADMIN")
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @Secured({"ADMIN", "USER"})
//    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/profile")
    public UserProfileResponse getUserProfile(Principal principal) {
        return userService.getProfile(principal);
    }


}
