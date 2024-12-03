package com.behindy.behindy_api.entity.fams;

import com.behindy.behindy_api.entity.comps.FamousLikeId;
import com.behindy.behindy_api.entity.users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`famous_like`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamousLike {
  @EmbeddedId
  private FamousLikeId id;

  @MapsId("userId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @MapsId("famousId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fam_id")
  private Famous famous;

  @Builder
  public FamousLike(User user, Famous famous) {
    this.id = new FamousLikeId(user.getId(), famous.getId());
    this.user = user;
    this.famous = famous;
  }
}