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
public class ItemEffectId implements Serializable {
  @Column(name = "itm_eff_stat")
  private String effectStat;
  private Long itemId;
}
