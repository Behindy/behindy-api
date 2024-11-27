package com.behindy.behindy_api.entity;

import com.behindy.behindy_api.utils.UlidGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Entity
@Table(name = "`user`")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  @Column(length = 32)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  // 논리삭제를 위한 필드 추가
  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  @Builder
  public User(String name, String email, String password) {
    this.id = UlidGenerator.generateUserId();
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public static User from(TempUser tempUser) {
    return User.builder()
        .name(tempUser.getName())
        .email(tempUser.getEmail())
        .password(tempUser.getPassword())
        .build();
  }

  // 논리삭제 메서드
  public void delete() {
    this.deletedAt = LocalDateTime.now();
  }

  // 삭제 여부 확인 메서드
  public boolean isDeleted() {
    return this.deletedAt != null;
  }
}
