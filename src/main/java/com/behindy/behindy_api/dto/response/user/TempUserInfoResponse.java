package com.behindy.behindy_api.dto.response.user;

import com.behindy.behindy_api.entity.User;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TempUserInfoResponse extends UserBaseResponse{
  private String message;

  public static TempUserInfoResponse of(User user, String message) {
    return TempUserInfoResponse.builder()
        .email(user.getEmail())
        .name(user.getName())
        .message(message)
        .build();
  }
}
