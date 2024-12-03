package com.behindy.behindy_api.entity.games;

import com.behindy.behindy_api.entity.metros.MetroStation;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`epi`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Episode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "epi_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "m_st_id", nullable = false)
  private MetroStation station;

  @Column(name = "sbj", nullable = false)
  private String subject;

  @Column(nullable = false)
  private Integer length;

  @OneToMany(mappedBy = "episode")
  private List<EpisodeScene> scenes = new ArrayList<>();

  @Builder
  public Episode(MetroStation station, String subject, Integer length) {
    this.station = station;
    this.subject = subject;
    this.length = length;
  }
}