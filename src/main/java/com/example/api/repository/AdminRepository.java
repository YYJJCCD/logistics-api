package com.example.api.repository;

import com.example.api.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    boolean existsAdminByEmail(String email);

    boolean existsAdminByRoles(String roles);

    Admin findAdminByEmailAndPassword(String email, String password);

    Admin findAdminByEmail(String email);

    @Transactional
    void deleteByUid(String uid);
}
