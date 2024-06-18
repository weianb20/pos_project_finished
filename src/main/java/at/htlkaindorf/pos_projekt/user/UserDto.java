package at.htlkaindorf.pos_projekt.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {

    @NotEmpty(message = "Email shouldn't be empty")
    private String email;

    @NotEmpty(message = "Password shouldn't be empty")
    private String password;

    private String firstName;

    private String lastName;

}
