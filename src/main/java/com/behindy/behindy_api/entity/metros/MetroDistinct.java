package com.behindy.behindy_api.entity.metros;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`metro_distinct`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MetroDistinct {
  @Id
  @Column(name = "m_d_id")
  private String id;

  @Column(name = "m_d_name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "distinct")
  private List<MetroStation> stations = new ArrayList<>();

  @OneToMany(mappedBy = "distinct")
  private List<MetroPath> paths = new ArrayList<>();

  @OneToMany(mappedBy = "distinct")
  private List<MetroStationLine> stationLines = new ArrayList<>();

  @Builder
  public MetroDistinct(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
