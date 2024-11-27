package com.behindy.behindy_api.service;

import com.behindy.behindy_api.dto.request.user.UserSignupRequest;
import com.behindy.behindy_api.dto.response.user.TempUserInfoResponse;
import com.behindy.behindy_api.entity.TempUser;
import com.behindy.behindy_api.entity.User;
import com.behindy.behindy_api.exception.DuplicateEmailException;
import com.behindy.behindy_api.exception.InvalidTokenException;
import com.behindy.behindy_api.exception.TokenExpiredException;
import com.behindy.behindy_api.repository.TempUserRepository;
import com.behindy.behindy_api.repository.UserRepository;
import com.behindy.behindy_api.service.email.EmailService;
import com.behindy.behindy_api.utils.TokenGenerator;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
  private final UserRepository userRepository;
  private final TempUserRepository tempUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final EmailService emailService;  // 추가

  @Transactional
  public User signup(UserSignupRequest request) {
    // 이메일 중복 검사
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("이미 가입된 이메일입니다");
    }

    // 비밀번호 암호화
    String encodedPassword = passwordEncoder.encode(request.getPassword());

    // 회원 생성
    User user = User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(encodedPassword)
        .build();

    return userRepository.save(user);
  }

  @Transactional
  public void initiateSignup(UserSignupRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new DuplicateEmailException();
    }

    String verificationToken = TokenGenerator.generateToken();
    // 비밀번호 암호화
    request.setPassword(passwordEncoder.encode(request.getPassword()));
    TempUser tempUser = TempUser.from(request, verificationToken);
    tempUserRepository.save(tempUser);

    emailService.sendVerificationEmail(request.getEmail(), verificationToken);
  }

  @Transactional
  public void completeSignup(String token) {
    TempUser tempUser = tempUserRepository.findByVerificationToken(token)
        .orElseThrow(() -> new InvalidTokenException());

    if (tempUser.isTokenExpired()) {
      throw new TokenExpiredException();
    }

    User user = User.from(tempUser);
    userRepository.save(user);

    tempUserRepository.delete(tempUser);
  }

  @Transactional(readOnly = true)
  public TempUserInfoResponse getCurrentUser(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

    return TempUserInfoResponse.of(user, "안녕하세요, " + user.getName() + "님!");
  }
}