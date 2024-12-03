package com.behindy.behindy_api.entity.metros;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`metro_line`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MetroLine {
  @Id
  @Column(name = "m_l_id")
  private String id;

  @Column(name = "m_l_name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "line")
  private List<MetroStationLine> stationLines = new ArrayList<>();

  @OneToMany(mappedBy = "line")
  private List<MetroPath> paths = new ArrayList<>();

  @Builder
  public MetroLine(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
