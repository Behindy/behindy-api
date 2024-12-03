package com.behindy.behindy_api.entity.fams;

import com.behindy.behindy_api.entity.metros.MetroStation;
import com.behindy.behindy_api.entity.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`fam`")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Famous {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "fam_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "m_st_id", nullable = false)
  private MetroStation station;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String name;

  // 좋아요 관계
  @OneToMany(mappedBy = "famous")
  private List<FamousLike> likes = new ArrayList<>();

  @Builder
  public Famous(MetroStation station, User user, String name) {
    this.station = station;
    this.user = user;
    this.name = name;
  }
}