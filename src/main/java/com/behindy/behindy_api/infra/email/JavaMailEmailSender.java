package com.behindy.behindy_api.infra.email;

import com.behindy.behindy_api.exception.EmailSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JavaMailEmailSender implements EmailSender {
  private final JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String fromEmail;

  @Override
  public void send(String to, String subject, String content) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setFrom(fromEmail);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content, true);  // HTML 형식 사용

      javaMailSender.send(message);
    } catch (MessagingException e) {
      throw new EmailSendException("이메일 전송 중 오류가 발생했습니다.", e);
    }
  }
}
