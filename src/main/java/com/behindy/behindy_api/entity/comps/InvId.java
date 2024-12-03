package com.behindy.behindy_api.entity.comps;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InvId implements Serializable {
  private Long characterId;
  private Long itemId;
}