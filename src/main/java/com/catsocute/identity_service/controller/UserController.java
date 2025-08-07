package com.catsocute.identity_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.catsocute.identity_service.model.User;
import com.catsocute.identity_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // create user
    @PostMapping()
    ApiResponse<User> createUSer(@RequestBody UserCreationRequest request) {
        ApiResponse apiResponse = new ApiResponse<>();
        User user = userService.createUser(request);

        apiResponse.setResult(user);
        return apiResponse;
    }

    // get all users
    @GetMapping()
    ApiResponse<List<User>> getUsers() {
        ApiResponse apiResponse = new ApiResponse<>();
        List<User> list = userService.getUsers();

        apiResponse.setResult(list);
        return apiResponse;
    }

    // get user by id
    @GetMapping("/{userId}")
    ApiResponse<User> getUser(@PathVariable("userId") String userId) {
        ApiResponse apiResponse = new ApiResponse<>();
        User user = userService.getUser(userId);

        apiResponse.setResult(user);

        return apiResponse;
    }

    // update by id
    @PutMapping("/{userId}")
    ApiResponse<User> updateUser(@PathVariable("userId") String userId,
            @RequestBody UserUpdateRequest request) {
        ApiResponse apiResponse = new ApiResponse<>();
        User user = userService.getUser(userId);

        apiResponse.setResult(user);
        return apiResponse;
    }

    // delete user by id
    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable("userId") String userId) {
        ApiResponse apiResponse = new ApiResponse<>();
        userService.deleteUser(userId);

        apiResponse.setResult("Delete successfully!!!");
        return apiResponse;
    }

    // delete all user
    @DeleteMapping()
    ApiResponse<String> deleteAll() {
        ApiResponse apiResponse = new ApiResponse<>();

        userService.deleteAll();
        apiResponse.setResult("Delete successfully!!!");

        return apiResponse;
    }

}
