package com.behindy.behindy_api.entity.users;

import com.behindy.behindy_api.entity.games.Character;
import com.behindy.behindy_api.entity.fams.Famous;
import com.behindy.behindy_api.utils.UlidGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`user`")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  @Column(name = "user_id", length = 32)
  private String id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = true)
  private LocalDateTime updatedAt;

  @Column(nullable = true)
  private LocalDateTime deletedAt;

  @OneToMany(mappedBy = "user")
  private List<Famous> famousLikes = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Character> characters = new ArrayList<>();

  @Builder
  public User(String email, String name, String password) {
    this.id = UlidGenerator.generateUserId();
    this.email = email;
    this.name = name;
    this.password = password;
  }

  public static User from(TempUser tempUser) {
    return User.builder()
        .email(tempUser.getEmail())
        .name(tempUser.getName())
        .password(tempUser.getPassword())
        .build();
  }

  public void delete() {
    this.deletedAt = LocalDateTime.now();
  }

  public boolean isDeleted() {
    return this.deletedAt != null;
  }
}