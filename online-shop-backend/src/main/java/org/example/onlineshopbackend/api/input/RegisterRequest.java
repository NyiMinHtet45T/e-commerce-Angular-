package org.example.onlineshopbackend.api.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.onlineshopbackend.model.entity.User;
import org.example.onlineshopbackend.model.entity.User.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Please enter name")
    private String name;
    @NotBlank(message = "Please enter email")
    private String email;
    @NotBlank(message = "Please enter password")
    private String password;
    @NotBlank(message = "Please enter phoneNumber")
    private String phoneNumber;

    public User registerRequestToUser(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUserRole(UserRole.CUSTOMER);
        user.setPhoneNumber(phoneNumber);
        return user;
    }
}
