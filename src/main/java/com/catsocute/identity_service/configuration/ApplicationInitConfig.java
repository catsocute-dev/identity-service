package com.catsocute.identity_service.configuration;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.catsocute.identity_service.enums.RolesEnum;
import com.catsocute.identity_service.exception.AppException;
import com.catsocute.identity_service.exception.ErrorCode;
import com.catsocute.identity_service.model.Role;
import com.catsocute.identity_service.model.User;
import com.catsocute.identity_service.repository.RoleRepository;
import com.catsocute.identity_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    RoleRepository roleRepository;
    
    @Bean
    ApplicationRunner applicationRunner(PasswordEncoder passwordEncoder,
        UserRepository userRepository) {
        
        return args -> {
            //check admin existed
            boolean existed = userRepository.existsByUsername("admin");
            if(existed == false) {

                //set role = ADMIN
                Role role = roleRepository.findById(RolesEnum.ADMIN.name())
                    .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
                HashSet<Role> roles = new HashSet<>();
                roles.add(role);

                User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .createdAt(LocalDate.now())
                    .roles(roles)
                    .build();

                userRepository.save(admin);
            }
        };
    }
}
