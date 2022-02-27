package com.example.api.repository;

import com.example.api.model.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, String> {
    Code findCodeByEmailAndValue(String email, String value);
}
