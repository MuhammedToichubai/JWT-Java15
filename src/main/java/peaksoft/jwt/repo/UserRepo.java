package peaksoft.jwt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import peaksoft.jwt.dto.response.UserProfileResponse;
import peaksoft.jwt.models.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findUserByEmailEqualsIgnoreCase(String email);

    @Query("SELECT u from User u")
    List<User> findAllUsers();


    @Query("""
            select  new peaksoft.jwt.dto.response.UserProfileResponse(
                        u.id, u.name, u.email, u.role
                        )
                  from User u where u.email = :email
            """)
    UserProfileResponse getProfileByEmail(String email);
}


