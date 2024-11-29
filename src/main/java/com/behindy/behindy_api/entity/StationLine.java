package com.behindy.behindy_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`station_lines`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StationLine {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "station_line_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "station_id")
  private Station station;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "line_id")
  private SubwayLine line;

  @Column(nullable = false)
  private Integer sequence;  // 노선 내 순서

  @Builder
  public StationLine(Station station, SubwayLine line, Integer sequence) {
    this.station = station;
    this.line = line;
    this.sequence = sequence;
  }
}
