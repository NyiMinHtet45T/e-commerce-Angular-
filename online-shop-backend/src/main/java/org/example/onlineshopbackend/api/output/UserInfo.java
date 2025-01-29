package org.example.onlineshopbackend.api.output;

import org.example.onlineshopbackend.model.entity.User;

public record UserInfo(
         Long id,
         String name,
         String email,
         String phoneNumber
) {
    public static UserInfo getUserInfo(User user) {
        return new UserInfo(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber());
    }
}
