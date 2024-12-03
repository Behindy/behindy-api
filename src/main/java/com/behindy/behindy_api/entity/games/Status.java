package com.behindy.behindy_api.entity.games;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "`stat`")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Status {
  @Id
  @Column(name = "ch_id")
  private Long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "ch_id")
  private Character character;

  @Column(nullable = false)
  private String mbti;

  @Column(nullable = false)
  private Integer hp = 100;

  @Column(nullable = false)
  private Integer san = 100;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = true)
  private LocalDateTime updatedAt;

  @Builder
  public Status(Character character, String mbti) {
    this.character = character;
    this.mbti = mbti;
  }

  public void updateHp(Integer amount) {
    this.hp = Math.max(0, Math.min(100, this.hp + amount));
  }

  public void updateSan(Integer amount) {
    this.san = Math.max(0, Math.min(100, this.san + amount));
  }
}