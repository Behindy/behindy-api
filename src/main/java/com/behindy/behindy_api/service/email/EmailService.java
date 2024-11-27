package com.behindy.behindy_api.service.email;

import com.behindy.behindy_api.infra.email.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
  @Value("${app.domain}")
  private String domain;

  private final EmailTemplateService emailTemplateService;
  private final EmailSender emailSender;

  public void sendVerificationEmail(String email, String token) {
    String verificationLink = generateVerificationLink(token);
    String emailContent = emailTemplateService.getVerificationTemplate(verificationLink);
    emailSender.send(email, "회원가입 인증", emailContent);
  }

  private String generateVerificationLink(String token) {
    return String.format("%s/api/user/signup/verify?token=%s", domain, token);
  }
}
