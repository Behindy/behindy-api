package com.behindy.behindy_api.entity.metros;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`metro_path`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MetroPath {
  @Id
  @Column(name = "m_p_id")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "m_l_id", nullable = false)
  private MetroLine line;

  @Column(name = "m_p_con_st_1", nullable = true)
  private String connectStation1;

  @Column(name = "m_p_con_st_2", nullable = false)
  private String connectStation2;

  @Builder
  public MetroPath(String id, MetroLine line,
                   String connectStation1, String connectStation2) {
    this.id = id;
    this.line = line;
    this.connectStation1 = connectStation1;
    this.connectStation2 = connectStation2;
  }
}