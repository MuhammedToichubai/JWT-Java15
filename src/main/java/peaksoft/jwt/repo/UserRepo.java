package peaksoft.jwt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import peaksoft.jwt.models.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findUserByEmailEqualsIgnoreCase(String email);

    @Query("SELECT u from User u")
    List<User> findAllUsers();
}


