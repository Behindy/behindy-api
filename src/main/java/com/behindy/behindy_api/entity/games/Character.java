package com.behindy.behindy_api.entity.games;

import com.behindy.behindy_api.entity.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`character`")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Character {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ch_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String mbti;

  @Column(name = "ch_name", nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer loop = 1;

  @Column(nullable = false)
  private Integer step = 1;

  @Column(name = "is_alive", nullable = false)
  private Boolean isAlive = true;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = true)
  private LocalDateTime updatedAt;

  @Column(nullable = true)
  private LocalDateTime deletedAt;

  // 인벤토리 관계
  @OneToMany(mappedBy = "character")
  private List<Inventory> inventories = new ArrayList<>();

  // 위치 관계
  @OneToMany(mappedBy = "character")
  private List<Location> locations = new ArrayList<>();

  // 스탯 관계
  @OneToOne(mappedBy = "character")
  private Status stat;

  @Builder
  public Character(User user, String mbti, String name) {
    this.user = user;
    this.mbti = mbti;
    this.name = name;
  }

  public void updateStep(Integer step) {
    this.step = step;
  }

  public void incrementLoop() {
    this.loop += 1;
    this.step = 1;  // 루프가 증가하면 step 초기화
  }

  public void die() {
    this.isAlive = false;
  }

  public void delete() {
    this.deletedAt = LocalDateTime.now();
  }
}