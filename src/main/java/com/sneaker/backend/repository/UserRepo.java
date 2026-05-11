package com.sneaker.backend.repository;

import com.sneaker.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    @Query(""" 
            SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END
            FROM UserEntity u WHERE u.email = :email AND u.phone = :phone
            """)
    boolean existsByEmailAndPhone(String email, String phone);
}
