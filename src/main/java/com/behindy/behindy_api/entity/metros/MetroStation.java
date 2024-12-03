package com.behindy.behindy_api.entity.metros;

import com.behindy.behindy_api.entity.fams.Famous;
import com.behindy.behindy_api.entity.games.Episode;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`metro_station`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MetroStation {
  @Id
  @Column(name = "m_st_id")
  private String id;

  @Column(name = "m_st_name", nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "m_d_id", nullable = false)
  private MetroDistinct distinct;

  @OneToMany(mappedBy = "station")
  private List<MetroStationLine> stationLines = new ArrayList<>();

  @OneToMany(mappedBy = "station")
  private List<Famous> famouses = new ArrayList<>();

  @OneToMany(mappedBy = "station")
  private List<Episode> episodes = new ArrayList<>();

  @OneToOne(mappedBy = "station")
  private MetroLevel level;

  @Builder
  public MetroStation(String id, String name, MetroDistinct distinct) {
    this.id = id;
    this.name = name;
    this.distinct = distinct;
  }
}
