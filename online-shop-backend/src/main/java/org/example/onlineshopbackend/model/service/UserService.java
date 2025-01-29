package org.example.onlineshopbackend.model.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.output.UserInfo;
import org.example.onlineshopbackend.model.repo.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserInfo getUserById(Long userId) {
        return UserInfo.getUserInfo(userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

}
