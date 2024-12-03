package com.behindy.behindy_api.entity.games;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`item`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "itm_id")
  private Long id;

  @Column(nullable = false)
  private Integer type;

  @Column(nullable = true)
  private String name;

  @Column(name = "desc", nullable = true)
  private String description;

  @OneToMany(mappedBy = "item")
  private List<ItemEffect> effects = new ArrayList<>();

  @OneToMany(mappedBy = "item")
  private List<Inventory> inventories = new ArrayList<>();

  @Builder
  public Item(Integer type, String name, String description) {
    this.type = type;
    this.name = name;
    this.description = description;
  }
}
