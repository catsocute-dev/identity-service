package com.catsocute.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catsocute.identity_service.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    
}
