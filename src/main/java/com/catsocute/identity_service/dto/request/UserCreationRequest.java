package com.catsocute.identity_service.dto.request;

import java.time.LocalDate;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class UserCreationRequest {
    private String username;
    private String email;
    private String password;
    private LocalDate createdAt;
}
