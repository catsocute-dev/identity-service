package com.catsocute.identity_service.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catsocute.identity_service.dto.request.UserCreationRequest;
import com.catsocute.identity_service.dto.request.UserUpdateRequest;
import com.catsocute.identity_service.dto.response.ApiResponse;
import com.catsocute.identity_service.enums.Role;
import com.catsocute.identity_service.model.User;
import com.catsocute.identity_service.service.UserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    // create user
    @PostMapping()
    ApiResponse<User> createUSer(@RequestBody @Valid UserCreationRequest request) {
        User user = userService.createUser(request);

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return ApiResponse.<User>builder()
            .result(user)
            .build();
    }

    // get all users
    @GetMapping()
    ApiResponse<List<User>> getUsers() {
        List<User> list = userService.getUsers();
        return ApiResponse.<List<User>>builder()
            .result(list)
            .build();
    }

    // get user by id
    @GetMapping("/{userId}")
    ApiResponse<User> getUser(@PathVariable("userId") String userId) {
        User user = userService.getUser(userId);
        return ApiResponse.<User>builder()
            .result(user)
            .build();
    }

    // update by id
    @PutMapping("/{userId}")
    ApiResponse<User> updateUser(@PathVariable("userId") String userId,
            @RequestBody UserUpdateRequest request) {
        User user = userService.updateUser(userId, request);
        return ApiResponse.<User>builder()
            .result(user)
            .build();
    }

    // delete user by id
    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
            .result("Delete successfully!!!")
            .build();
    }

    // delete all user
    @DeleteMapping()
    ApiResponse<String> deleteAll() {
        userService.deleteAll();
        return ApiResponse.<String>builder()
            .result("Delete successfully!!!")
            .build();
    }

}
