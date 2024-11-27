package com.behindy.behindy_api.service.email;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService {
  public String getVerificationTemplate(String verificationLink) {
    return String.format("""
            <div style="margin: 20px;">
                <h2>회원가입 인증</h2>
                <p>아래 링크를 클릭하여 회원가입을 완료해주세요.</p>
                <a href="%s" style="
                    display: inline-block;
                    padding: 10px 20px;
                    background-color: #007bff;
                    color: white;
                    text-decoration: none;
                    border-radius: 5px;">
                    이메일 인증하기
                </a>
                <p>이 링크는 24시간 동안 유효합니다.</p>
            </div>
            """, verificationLink);
  }
}
