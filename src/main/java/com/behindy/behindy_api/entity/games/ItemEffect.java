package com.behindy.behindy_api.entity.games;


import com.behindy.behindy_api.entity.comps.ItemEffectId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`item_effect`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEffect {
  @EmbeddedId
  private ItemEffectId id;

  @MapsId("itemId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "itm_id")
  private Item item;

  @Column(name = "itm_eff_val", nullable = false)
  private Integer effectValue;

  @Builder
  public ItemEffect(Item item, String effectStat, Integer effectValue) {
    this.id = new ItemEffectId(effectStat, item.getId());
    this.item = item;
    this.effectValue = effectValue;
  }
}
