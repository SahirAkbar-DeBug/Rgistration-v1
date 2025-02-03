package com.api.repository;

import com.api.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
@Query("select r from Registration r where r.email=:x OR r.name=:y")
    public Registration findByNameOrEmail(@Param("x") String email, @Param("y") String name);
}