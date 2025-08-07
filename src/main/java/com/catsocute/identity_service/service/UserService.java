package com.catsocute.identity_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catsocute.identity_service.dto.request.UserCreationRequest;
import com.catsocute.identity_service.dto.request.UserUpdateRequest;
import com.catsocute.identity_service.exception.AppException;
import com.catsocute.identity_service.exception.ErrorCode;
import com.catsocute.identity_service.model.User;
import com.catsocute.identity_service.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //create user
    public User createUser(UserCreationRequest request) {
        //validate user existed
        boolean existed = userRepository.existsByUsername(request.getUsername());
        if(existed) {
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setCreatedAt(request.getCreatedAt());

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


        User user = getUser(userId);

        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
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
