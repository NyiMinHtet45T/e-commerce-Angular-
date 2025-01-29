package org.example.onlineshopbackend.api.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Getter
@Setter
public class LoginInRequest {

    @NotBlank(message = "Please enter your name")
    private String name;

    @NotBlank(message = "Please enter your password")
    private String password;

    public Authentication getUserAuthentication() {
        return new UsernamePasswordAuthenticationToken(name, password);
    }
}
