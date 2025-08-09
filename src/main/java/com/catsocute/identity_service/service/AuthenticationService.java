package com.catsocute.identity_service.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.catsocute.identity_service.dto.request.AuthenticationRequest;
import com.catsocute.identity_service.dto.response.AuthenticationResponse;
import com.catsocute.identity_service.exception.AppException;
import com.catsocute.identity_service.exception.ErrorCode;
import com.catsocute.identity_service.model.User;
import com.catsocute.identity_service.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;

    @Value("${security.jwt.signer-key}")
    @NonFinal
    String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        //match password
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        //check for unauthenticated
        if(authenticated == false) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        //generate JWTToken
        String token = generateToken(user);

        return AuthenticationResponse.builder()
            .authenticated(authenticated)
            .token(token)
            .build();
    }

    //generate JWT token using nimbus library
    private String generateToken(User user) {
        //header
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        //Payload
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
            .subject(user.getUsername())
            .issuer("catsocute")
            .issueTime(new Date())
            .expirationTime(
                Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
            .claim("userId", user.getUserId())
            .build();
        
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        //build JWSObject
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //signer
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token");
            throw new RuntimeException();
        }
    }
}
