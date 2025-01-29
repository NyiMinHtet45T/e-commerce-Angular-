package org.example.onlineshopbackend.model.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.ChangePasswordForm;
import org.example.onlineshopbackend.api.input.LoginInRequest;
import org.example.onlineshopbackend.api.input.RefreshForm;
import org.example.onlineshopbackend.api.input.RegisterRequest;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.api.output.SecurityInfo;
import org.example.onlineshopbackend.exception.ApiBusinessException;
import org.example.onlineshopbackend.model.entity.User;
import org.example.onlineshopbackend.model.repo.UserRepo;
import org.example.onlineshopbackend.security.JwtTokenProvider;
import org.example.onlineshopbackend.security.JwtTokenProvider.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public MessageResponse register(RegisterRequest registerRequest) {

        if (userRepo.findByName(registerRequest.getName()).isPresent()) {
            throw new ApiBusinessException("Your account is already registered. Please try to loginIn.");
        }
        userRepo.save(registerRequest.registerRequestToUser(passwordEncoder));

        return new MessageResponse("Successfully registered!");
    }

    public SecurityInfo login(LoginInRequest loginInRequest) {

        User user = userRepo.findByName(loginInRequest.getName()).orElseThrow(() -> new ApiBusinessException("User not found"));

        Authentication authentication = authenticationManager.authenticate(loginInRequest.getUserAuthentication());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return getSecurityObj(user, authentication);
    }

    public SecurityInfo refresh(RefreshForm refreshForm) {
        Authentication authentication = jwtTokenProvider.parse(refreshForm.token(), Type.Refresh);
        if (authentication == null) {
            throw new ApiBusinessException("Invalid token");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepo.findByName(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return getSecurityObj(user, authentication);
    }

    public SecurityInfo changePassword(ChangePasswordForm form) {

        User user = userRepo.findByName(form.username()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!passwordEncoder.matches(form.oldPassword(), user.getPassword())) {
            throw new ApiBusinessException("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(form.newPassword()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return getSecurityObj(userRepo.save(user), authentication);
    }

    private SecurityInfo getSecurityObj(User user, Authentication authentication) {
        return SecurityInfo.getSecurityInfo(user,
                jwtTokenProvider.generateToken(authentication, Type.Access),
                jwtTokenProvider.generateToken(authentication, Type.Refresh));
    }
}
