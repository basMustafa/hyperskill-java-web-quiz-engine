package engine.dto;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserDTO {

    @Email(regexp = ".*@.*\\..*")
    private String email;
    @Size(min = 5)
    private String password;
}
