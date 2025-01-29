package org.example.onlineshopbackend.security;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.model.repo.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByName(username)
                .map(u -> User.withUsername(u.getName())
                        .password(u.getPassword())
                        .authorities(u.getUserRole().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
