package com.behindy.behindy_api.entity.games;

import com.behindy.behindy_api.entity.comps.EpisodeChoiceId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`epi_choice`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EpisodeChoice {
  @EmbeddedId
  private EpisodeChoiceId id;

  @MapsId("sceneId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "epi_sc_id")
  private EpisodeScene scene;

  @Column(name = "disp_cond", nullable = true)
  private String displayCondition;

  @Column(name = "disp_level", nullable = true)
  private Integer displayLevel;

  @Column(name = "desc", nullable = false)
  private String description;

  @Column(name = "cond", nullable = true)
  private String condition;

  @Column(name = "cond_level", nullable = true)
  private Integer conditionLevel;

  @Column(name = "suc_effect", nullable = true)
  private String successEffect;

  @Column(name = "suc_effect_level", nullable = true)
  private Integer successEffectLevel;

  @Column(name = "suc_next", nullable = true)
  private Integer successNext;

  @Column(name = "fail_effect", nullable = true)
  private String failEffect;

  @Column(name = "fail_effect_level", nullable = true)
  private Integer failEffectLevel;

  @Column(name = "fail_next", nullable = true)
  private Integer failNext;

  @Builder
  public EpisodeChoice(EpisodeScene scene, Integer choiceNumber, String description) {
    this.id = new EpisodeChoiceId(choiceNumber, scene.getId());
    this.scene = scene;
    this.description = description;
  }

  @Builder(builderMethodName = "fullBuilder")
  public EpisodeChoice(EpisodeScene scene, Integer choiceNumber, String displayCondition, Integer displayLevel,
                       String description, String condition, Integer conditionLevel,
                       String successEffect, Integer successEffectLevel, Integer successNext,
                       String failEffect, Integer failEffectLevel, Integer failNext) {
    this.id = new EpisodeChoiceId(choiceNumber, scene.getId());
    this.scene = scene;
    this.displayCondition = displayCondition;
    this.displayLevel = displayLevel;
    this.description = description;
    this.condition = condition;
    this.conditionLevel = conditionLevel;
    this.successEffect = successEffect;
    this.successEffectLevel = successEffectLevel;
    this.successNext = successNext;
    this.failEffect = failEffect;
    this.failEffectLevel = failEffectLevel;
    this.failNext = failNext;
  }
}
