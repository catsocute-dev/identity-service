package com.catsocute.identity_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.catsocute.identity_service.dto.request.UserCreationRequest;
import com.catsocute.identity_service.dto.request.UserUpdateRequest;
import com.catsocute.identity_service.model.User;
import com.catsocute.identity_service.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //create user
    @PostMapping()
    User createUSer(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }
    
    //get all users
    @GetMapping()
    List<User> getUsers() {
        return userService.getUsers();
    }

    //get user by id
    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    //update by id
    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") String userId ,
                    @RequestBody UserUpdateRequest request)  {
        return userService.updateUser(userId, request);
    }

    //delete user by id
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "Delete successfully";
    }

}
