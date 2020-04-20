package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
