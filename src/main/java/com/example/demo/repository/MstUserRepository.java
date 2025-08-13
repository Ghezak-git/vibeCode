package com.example.demo.repository;

import com.example.demo.entity.MstUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MstUserRepository extends JpaRepository<MstUser, Long> {
    Optional<MstUser> findByUsername(String username);
    Optional<MstUser> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
