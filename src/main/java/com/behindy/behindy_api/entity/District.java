package com.behindy.behindy_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`districts`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class District {
  @Id
  @Column(name = "district_id")
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String desc;

  @OneToMany(mappedBy = "district")
  private List<Station> stations = new ArrayList<>();

  @Builder
  public District(String id, String name, String desc) {
    this.id = id;
    this.name = name;
    this.desc = desc;
  }
}
