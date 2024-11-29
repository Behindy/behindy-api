package com.behindy.behindy_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`subway_lines`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubwayLine {
  @Id
  @Column(name = "line_id")
  private Integer id;  // 1, 2, 3 등 호선 번호

  @Column(nullable = false)
  private String name;  // 1호선, 2호선 등

  @OneToMany(mappedBy = "line")
  private List<StationLine> stationLines = new ArrayList<>();

  @OneToMany(mappedBy = "line")
  private List<LineConnection> connections = new ArrayList<>();

  @Builder
  public SubwayLine(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
