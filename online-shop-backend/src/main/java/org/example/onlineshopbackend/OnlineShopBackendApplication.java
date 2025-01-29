package org.example.onlineshopbackend;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.model.entity.User;
import org.example.onlineshopbackend.model.repo.UserRepo;
import org.example.onlineshopbackend.model.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class OnlineShopBackendApplication {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Profile("dev")
    public ApplicationRunner init() {
        return args -> {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setUserRole(User.UserRole.ADMIN);
            admin.setPhoneNumber("09322372");
           userRepo.save(admin);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopBackendApplication.class, args);
    }

}
