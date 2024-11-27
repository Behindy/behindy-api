package com.behindy.behindy_api.repository;


import com.behindy.behindy_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
  // 삭제되지 않은 사용자만 조회
  @Query("SELECT u FROM User u WHERE u.email = :email AND u.deletedAt IS NULL")
  Optional<User> findByEmail(String email);

  // 삭제되지 않은 사용자 중 이메일 중복 체크
  @Query("SELECT EXISTS (SELECT 1 FROM User u WHERE u.email = :email AND u.deletedAt IS NULL)")
  boolean existsByEmail(String email);
}