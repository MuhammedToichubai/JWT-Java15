package peaksoft.jwt.service;

import peaksoft.jwt.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

}
