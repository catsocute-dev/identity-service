package com.catsocute.identity_service.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.catsocute.identity_service.dto.request.RoleRequest;
import com.catsocute.identity_service.model.Permission;
import com.catsocute.identity_service.model.Role;
import com.catsocute.identity_service.repository.PermissionRepository;
import com.catsocute.identity_service.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    //create role
    public Role createRole(RoleRequest request) {
        //get permissions
        List<Permission> permissionList = permissionRepository.findAllById(request.getPermissions());
        HashSet<Permission> permissionSet = new HashSet<>(permissionList);
        Role role = Role.builder()
            .name(request.getName())
            .description(request.getDescription())
            .permissions(permissionSet)
            .build();
        return roleRepository.save(role);
    }

    //get roles
    public List<Role> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    //delete role
    public void deleteRole(String role) {
        roleRepository.deleteById(role);
    }
}
