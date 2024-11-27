package com.behindy.behindy_api.infra.email;

public interface EmailSender {
  void send(String to, String subject, String content);
}
