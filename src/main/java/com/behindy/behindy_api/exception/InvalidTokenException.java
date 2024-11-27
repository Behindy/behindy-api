package com.behindy.behindy_api.exception;

// 유효하지 않은 토큰 예외
public class InvalidTokenException extends BehindyApiException {
  public InvalidTokenException() {
    super("유효하지 않은 인증 토큰입니다.");
  }
}
