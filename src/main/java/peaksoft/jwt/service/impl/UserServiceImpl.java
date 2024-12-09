package peaksoft.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.jwt.models.User;
import peaksoft.jwt.repo.UserRepo;
import peaksoft.jwt.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAllUsers();
    }
}
