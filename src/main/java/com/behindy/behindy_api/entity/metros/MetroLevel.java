package com.behindy.behindy_api.entity.metros;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`metro_level`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MetroLevel {
  @Id
  @Column(name = "m_st_id")
  private String id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "m_st_id")
  private MetroStation station;

  @Column(name = "vict_cnt", nullable = false)
  private Integer victoryCount = 0;

  @Builder
  public MetroLevel(MetroStation station) {
    this.station = station;
    this.id = station.getId();
  }

  public void incrementVictoryCount() {
    this.victoryCount += 1;
  }
}
