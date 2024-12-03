package com.behindy.behindy_api.entity.games;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`mbti_base_stat`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbtiBaseStat {
  @Id
  @Column(nullable = false)
  private String mbti;

  @Column(nullable = false)
  private Integer hp = 100;

  @Column(nullable = false)
  private Integer san = 100;

  @Column(name = "str", nullable = false)
  private Integer strength = 5;

  @Column(name = "dex", nullable = false)
  private Integer dexterity = 5;

  @Column(name = "int", nullable = false)
  private Integer intelligence = 6;

  @Column(name = "cha", nullable = false)
  private Integer charisma = 5;

  @Column(nullable = false)
  private Integer will = 5;

  @Builder
  public MbtiBaseStat(String mbti, Integer hp, Integer san, Integer strength, Integer dexterity,
                      Integer intelligence, Integer charisma, Integer will) {
    this.mbti = mbti;
    this.hp = hp != null ? hp : 100;
    this.san = san != null ? san : 100;
    this.strength = strength != null ? strength : 5;
    this.dexterity = dexterity != null ? dexterity : 5;
    this.intelligence = intelligence != null ? intelligence : 6;
    this.charisma = charisma != null ? charisma : 5;
    this.will = will != null ? will : 5;
  }

  @Builder(builderMethodName = "mbtiOnlyBuilder")
  public MbtiBaseStat(String mbti) {
    this.mbti = mbti;
  }
}
