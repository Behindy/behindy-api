package com.behindy.behindy_api.entity.games;

import com.behindy.behindy_api.entity.comps.InvId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "`inv`")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory {

  @EmbeddedId
  private InvId id;

  @MapsId("characterId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ch_id")
  private Character character;

  @MapsId("itemId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "itm_id")
  private Item item;

  @Column(name = "qty")
  private Integer quantity;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = true)
  private LocalDateTime updatedAt;

  @Builder
  public Inventory(Character character, Item item, Integer quantity) {
    this.id = new InvId(character.getId(), item.getId());
    this.character = character;
    this.item = item;
    this.quantity = quantity;
  }

  public void addQuantity(Integer amount) {
    this.quantity += amount;
  }

  public void removeQuantity(Integer amount) {
    if (this.quantity < amount) {
      throw new IllegalStateException("Not enough items");
    }
    this.quantity -= amount;
  }
}