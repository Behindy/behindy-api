package com.behindy.behindy_api.entity.games;

import com.behindy.behindy_api.entity.comps.LocId;
import com.behindy.behindy_api.entity.metros.MetroStation;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "`loc`")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
  @EmbeddedId
  private LocId id;

  @MapsId("characterId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ch_id")
  private Character character;

  @MapsId("stationId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "m_st_id")
  private MetroStation station;

  @MapsId("episodeId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "epi_id")
  private Episode episode;

  @MapsId("sceneId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "epi_sc_id")
  private EpisodeScene scene;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Builder
  public Location(Character character, MetroStation station, Episode episode, EpisodeScene scene) {
    this.id = new LocId(character.getId(), station.getId(), episode.getId(), scene.getId());
    this.character = character;
    this.station = station;
    this.episode = episode;
    this.scene = scene;
  }
}
