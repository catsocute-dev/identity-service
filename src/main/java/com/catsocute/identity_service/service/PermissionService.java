package com.catsocute.identity_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catsocute.identity_service.dto.request.PermissionRequest;
import com.catsocute.identity_service.model.Permission;
import com.catsocute.identity_service.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;

    //create permission
    public Permission createPermission(PermissionRequest request) {
        Permission permission = Permission.builder()
            .name(request.getName())
            .description(request.getDescription())
            .build();
        return permissionRepository.save(permission);
    }

    //get parmissions
    public List<Permission> getPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions;
    }

    //delete permission
    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }
}
