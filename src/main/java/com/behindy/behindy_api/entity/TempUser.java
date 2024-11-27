package com.behindy.behindy_api.entity;

import com.behindy.behindy_api.dto.request.user.UserSignupRequest;
import com.behindy.behindy_api.utils.UlidGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "temp_user")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempUser {
  @Id
  @Column(length = 32)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String verificationToken;

  @Column(nullable = false)
  private LocalDateTime tokenExpireTime;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Builder
  public TempUser(String name, String email, String password, String verificationToken) {
    this.id = UlidGenerator.generateUserId();
    this.name = name;
    this.email = email;
    this.password = password;
    this.verificationToken = verificationToken;
    this.tokenExpireTime = LocalDateTime.now().plusHours(24);
  }

  public static TempUser from(UserSignupRequest request, String verificationToken) {
    return TempUser.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(request.getPassword())  // 암호화는 서비스 계층에서 처리
        .verificationToken(verificationToken)
        .build();
  }

  public boolean isTokenExpired() {
    return LocalDateTime.now().isAfter(this.tokenExpireTime);
  }

  public User toUser() {
    return User.builder()
        .name(this.name)
        .email(this.email)
        .password(this.password)
        .build();
  }
}