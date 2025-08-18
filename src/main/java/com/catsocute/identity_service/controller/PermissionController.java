package com.catsocute.identity_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catsocute.identity_service.dto.request.PermissionRequest;
import com.catsocute.identity_service.dto.response.ApiResponse;
import com.catsocute.identity_service.model.Permission;
import com.catsocute.identity_service.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    //create permission
    @PostMapping("/add")
    ApiResponse<Permission> createPermission(@RequestBody PermissionRequest request) {
        Permission permission = permissionService.createPermission(request);
        return ApiResponse.<Permission>builder()
            .result(permission)
            .build();
    }

    //get permissions
    @GetMapping("/get")
    ApiResponse<List<Permission>> getPermissions() {
        List<Permission> permissions = permissionService.getPermissions();
        return ApiResponse.<List<Permission>>builder()
            .result(permissions)
            .build();
    }

    //delete permission
    @DeleteMapping("/delete/{permission}")
    ApiResponse<String> deletePermissions(@PathVariable("permission") String permission) {
        permissionService.deletePermission(permission);
        return ApiResponse.<String>builder()
            .result("Delete succesfully")
            .build();
    }
}
