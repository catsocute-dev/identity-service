package com.catsocute.identity_service.mapper;

import org.mapstruct.Mapper;

import com.catsocute.identity_service.dto.request.UserCreationRequest;
import com.catsocute.identity_service.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
}
