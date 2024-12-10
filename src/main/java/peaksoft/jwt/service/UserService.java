package peaksoft.jwt.service;

import peaksoft.jwt.dto.response.UserProfileResponse;
import peaksoft.jwt.models.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<User> findAll();

    UserProfileResponse getProfile(Principal principal);

}
