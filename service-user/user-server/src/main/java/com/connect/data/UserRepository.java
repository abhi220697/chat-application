package com.connect.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    // Find user by email
    Optional<UserEntity> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Check if phone number exists
    boolean existsByPhoneNo(String phoneNo);
}