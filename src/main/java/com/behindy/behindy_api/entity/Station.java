package com.behindy.behindy_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`stations`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Station {
  @Id
  @Column(name = "station_id")
  private String id;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id")
  private District district;

  @OneToMany(mappedBy = "station")
  private List<StationLine> stationLines = new ArrayList<>();

  @Builder
  public Station(String id, String name, District district) {
    this.id = id;
    this.name = name;
    this.district =district;
  }
}
