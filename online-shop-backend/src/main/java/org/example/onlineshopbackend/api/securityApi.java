package org.example.onlineshopbackend.api;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.ChangePasswordForm;
import org.example.onlineshopbackend.api.input.LoginInRequest;
import org.example.onlineshopbackend.api.input.RefreshForm;
import org.example.onlineshopbackend.api.input.RegisterRequest;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.api.output.SecurityInfo;
import org.example.onlineshopbackend.model.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class securityApi {

    private final SecurityService securityService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest registerRequest, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(securityService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<SecurityInfo> login(@RequestBody LoginInRequest loginInRequest, BindingResult result) {
        return ResponseEntity.status(HttpStatus.OK).body(securityService.login(loginInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<SecurityInfo> refresh(@RequestBody RefreshForm refreshForm, BindingResult result) {
        return ResponseEntity.status(HttpStatus.OK).body(securityService.refresh(refreshForm));
    }

    @PostMapping("/change_password")
    public ResponseEntity<SecurityInfo> changePassword(@RequestBody ChangePasswordForm changePasswordForm, BindingResult result) {
        return ResponseEntity.status(HttpStatus.OK).body(securityService.changePassword(changePasswordForm));
    }

}
