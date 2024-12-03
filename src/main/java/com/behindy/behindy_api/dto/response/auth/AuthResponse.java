package com.behindy.behindy_api.dto.response.auth;

import com.behindy.behindy_api.dto.common.TokenInfo;
import com.behindy.behindy_api.entity.users.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {
  private TokenInfo tokenInfo;
  private User user;  // 또는 필요한 사용자 정보만 담은 DTO
}
