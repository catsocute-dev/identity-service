package com.catsocute.identity_service.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catsocute.identity_service.dto.request.AuthenticationRequest;
import com.catsocute.identity_service.dto.request.IntrospectRequest;
import com.catsocute.identity_service.dto.response.ApiResponse;
import com.catsocute.identity_service.dto.response.AuthenticationResponse;
import com.catsocute.identity_service.dto.response.IntrospectResponse;
import com.catsocute.identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticated(authenticationResponse.isAuthenticated())
                        .token(authenticationResponse.getToken())
                        .build())
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        IntrospectResponse introspectResponse = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .result(IntrospectResponse.builder()
                        .valid(introspectResponse.isValid())
                        .build())
                .build();
    }
}
