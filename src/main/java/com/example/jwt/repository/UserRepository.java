package com.example.jwt.repository;


import com.example.jwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT a FROM UserEntity a WHERE a.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);
}
