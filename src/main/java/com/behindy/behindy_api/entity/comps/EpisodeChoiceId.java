package com.behindy.behindy_api.entity.comps;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EpisodeChoiceId implements Serializable {
  @Column(name = "ch_number")
  private Integer choiceNumber;
  private Long sceneId;
}
