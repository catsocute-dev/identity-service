package com.catsocute.identity_service.configuration;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.catsocute.identity_service.enums.Role;
import com.catsocute.identity_service.model.User;
import com.catsocute.identity_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    
    @Bean
    ApplicationRunner applicationRunner(PasswordEncoder passwordEncoder,
        UserRepository userRepository) {
        
        return args -> {
            //check admin existed
            boolean existed = userRepository.existsByUsername("admin");
            if(existed == false) {
                HashSet<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());

                User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .createdAt(LocalDate.now())
                    // .roles(roles)
                    .build();

                userRepository.save(admin);
            }
        };
    }
}
