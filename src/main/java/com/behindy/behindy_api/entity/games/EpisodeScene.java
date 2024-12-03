package com.behindy.behindy_api.entity.games;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`epi_scene`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EpisodeScene {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "epi_sc_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "epi_id", nullable = false)
  private Episode episode;

  @Column(name = "desc", nullable = true)
  private String description;

  @Column(nullable = false)
  private Integer phase = 1;

  @Column(name = "is_last", nullable = false)
  private Boolean isLast = false;

  @OneToMany(mappedBy = "scene")
  private List<EpisodeChoice> choices = new ArrayList<>();

  @Builder
  public EpisodeScene(Episode episode, String description, Integer phase, Boolean isLast) {
    this.episode = episode;
    this.description = description;
    this.phase = phase;
    this.isLast = isLast;
  }
}
