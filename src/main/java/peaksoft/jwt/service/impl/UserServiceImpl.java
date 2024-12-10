package peaksoft.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.jwt.dto.response.UserProfileResponse;
import peaksoft.jwt.models.User;
import peaksoft.jwt.repo.UserRepo;
import peaksoft.jwt.service.UserService;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAllUsers();
    }

    @Override
    public UserProfileResponse getProfile(Principal principal) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = principal.getName();
        return userRepo.getProfileByEmail(email);
    }
}
