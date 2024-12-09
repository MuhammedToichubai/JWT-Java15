package peaksoft.jwt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import peaksoft.jwt.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findUserByEmailEqualsIgnoreCase(String email);
    @Query("SELECT u from User u")
    List<User> findAllUsers();
}


