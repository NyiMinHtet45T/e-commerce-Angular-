package org.example.onlineshopbackend.api.input;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordForm(
        @NotBlank(message = "Please enter the username") String username,
        @NotBlank(message = "Please enter the old Password") String oldPassword,
        @NotBlank(message = "Please enter the new Password") String newPassword
) {
}
