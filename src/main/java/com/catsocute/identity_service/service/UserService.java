package com.catsocute.identity_service.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.catsocute.identity_service.dto.request.UserCreationRequest;
import com.catsocute.identity_service.dto.request.UserUpdateRequest;
import com.catsocute.identity_service.enums.Role;
import com.catsocute.identity_service.exception.AppException;
import com.catsocute.identity_service.exception.ErrorCode;
import com.catsocute.identity_service.model.User;
import com.catsocute.identity_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    //create user
    public User createUser(UserCreationRequest request) {
        //validate user existed
        boolean existed = userRepository.existsByUsername(request.getUsername());
        if(existed) {
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }

        var roles = new HashSet<String>();
        roles.add(Role.USER.name());

        User user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .email(request.getEmail())
        .createdAt(request.getCreatedAt())
        .roles(roles)
        .build();
        
        return userRepository.save(user);
    }

    //get all users
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    //get user
    public User getUser(String id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    //update user
    public User updateUser(String userId, UserUpdateRequest request) {
        //validate user existed
        boolean existed = userRepository.existsById(userId);
        if(existed == false) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        //get user need to update
        User user = getUser(userId);

        //update by toBuilder() -- lombok
        User updatedUser = user.toBuilder()
        .password(passwordEncoder.encode(request.getPassword()))
        .email(request.getEmail())
        .build();

        return userRepository.save(updatedUser);
    }

    //delete user by id
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    //delete all users
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
