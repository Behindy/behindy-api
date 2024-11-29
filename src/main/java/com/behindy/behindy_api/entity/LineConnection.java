package com.behindy.behindy_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`line_connections`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LineConnection {
  @Id
  @Column(name = "connection_id")
  private String id;  // p1, p2 등 SVG의 id값

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "from_station_id")
  private Station fromStation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "to_station_id")
  private Station toStation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "line_id")
  private SubwayLine line;

  @Builder
  public LineConnection(String id, Station fromStation, Station toStation, SubwayLine line) {
    this.id = id;
    this.fromStation = fromStation;
    this.toStation = toStation;
    this.line = line;
  }
}
