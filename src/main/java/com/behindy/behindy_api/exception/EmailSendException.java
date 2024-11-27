package com.behindy.behindy_api.exception;

// 이메일 전송 실패 예외
public class EmailSendException extends BehindyApiException {
  public EmailSendException() {
    super("이메일 전송 중 오류가 발생했습니다.");
  }

  public EmailSendException(String message) {
    super(message);
  }

  public EmailSendException(String message, Throwable cause) {
    super(message, cause);
  }
}
