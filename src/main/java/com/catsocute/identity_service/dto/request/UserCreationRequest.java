package com.catsocute.identity_service.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;
    private String email;
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private LocalDate createdAt;
}
