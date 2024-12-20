package peaksoft.jwt.dto.response;

import lombok.Getter;
import lombok.Setter;
import peaksoft.jwt.models.Role;

@Getter
@Setter
public class UserProfileResponse {
    private String title = "USER PROFILE";
    private Long id;
    private String name;
    private String email;
    private Role role;

    public UserProfileResponse(Long id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
