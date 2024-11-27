package com.behindy.behindy_api.exception;


// 토큰 만료 예외
public class TokenExpiredException extends BehindyApiException {
  public TokenExpiredException() {
    super("인증 토큰이 만료되었습니다.");
  }
}