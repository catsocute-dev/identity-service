package com.catsocute.identity_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catsocute.identity_service.dto.request.RoleRequest;
import com.catsocute.identity_service.dto.response.ApiResponse;
import com.catsocute.identity_service.model.Role;
import com.catsocute.identity_service.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("/roles")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    //create role
    @PostMapping("/add")
    ApiResponse<Role> createRole(@RequestBody RoleRequest request) {
        Role role = roleService.createRole(request);
        return ApiResponse.<Role>builder()
            .result(role)
            .build();
    }

    //get roles
    @GetMapping("/get")
    ApiResponse<List<Role>> getRoles() {
        List<Role> roles = roleService.getRoles();
        return ApiResponse.<List<Role>>builder()
            .result(roles)
            .build();
    }

    //delete role
    @DeleteMapping("/delete/{role}")
    ApiResponse<String> deleteRole(@PathVariable("role") String role) {
        roleService.deleteRole(role);
        return ApiResponse.<String>builder()
            .result("Delete succesfully")
            .build();
    }
}
