package com.behindy.behindy_api.entity.metros;

import com.behindy.behindy_api.entity.comps.MetroStationLineId;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`metro_station_line`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MetroStationLine {
  @EmbeddedId
  private MetroStationLineId id;

  @MapsId("stationId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "m_st_id")
  private MetroStation station;

  @MapsId("lineId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "m_l_id")
  private MetroLine line;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "m_d_id", nullable = false)
  private MetroDistinct distinct;

  @Builder
  public MetroStationLine(MetroStation station, MetroLine line, MetroDistinct distinct) {
    this.id = new MetroStationLineId(station.getId(), line.getId());
    this.station = station;
    this.line = line;
    this.distinct = distinct;
  }
}