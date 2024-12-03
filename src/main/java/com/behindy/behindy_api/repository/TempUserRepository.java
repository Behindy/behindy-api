package com.behindy.behindy_api.repository;

import com.behindy.behindy_api.entity.users.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempUserRepository extends JpaRepository<TempUser, String> {
  Optional<TempUser> findByEmail(String email);
  Optional<TempUser> findByVerificationToken(String token);
  boolean existsByEmail(String email);
  void deleteByVerificationToken(String token);
}
