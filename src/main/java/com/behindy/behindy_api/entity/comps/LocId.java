package com.behindy.behindy_api.entity.comps;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LocId implements Serializable {
  private Long characterId;
  private String stationId;
  private Long episodeId;
  private Long sceneId;
}
