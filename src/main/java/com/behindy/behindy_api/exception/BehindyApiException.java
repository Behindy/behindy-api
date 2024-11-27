package com.behindy.behindy_api.exception;

// 기본 커스텀 예외 클래스
public class BehindyApiException extends RuntimeException {
  public BehindyApiException(String message) {
    super(message);
  }

  public BehindyApiException(String message, Throwable cause) {
    super(message, cause);
  }
}

