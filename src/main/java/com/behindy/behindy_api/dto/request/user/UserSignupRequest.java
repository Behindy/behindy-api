package com.behindy.behindy_api.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequest {
  @NotBlank(message = "이름은 필수입니다")
  private String name;

  @Email(message = "유효한 이메일을 입력해주세요")
  @NotBlank(message = "이메일은 필수입니다")
  private String email;

  @NotBlank(message = "비밀번호는 필수입니다")
  @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다")
  private String password;
}