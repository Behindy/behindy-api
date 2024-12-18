// MemberController.java
package com.behindy.behindy_api.controller;

import com.behindy.behindy_api.dto.request.user.UserSignupRequest;
import com.behindy.behindy_api.dto.response.ApiResponse;
import com.behindy.behindy_api.dto.response.user.TempUserInfoResponse;
import com.behindy.behindy_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<Void>> signup(@Valid @RequestBody UserSignupRequest request) {
    userService.signup(request);

    return ResponseEntity.ok()
        .body(ApiResponse.success(null, "회원가입에 성공했습니다."));
  }

  @PostMapping("/signup/initiate")
  public ResponseEntity<ApiResponse> initiateSignup(@Valid @RequestBody UserSignupRequest request) {
    userService.initiateSignup(request);
    return ResponseEntity.ok(ApiResponse.success("인증 메일이 발송되었습니다"));
  }

  @GetMapping("/signup/verify")
  public ResponseEntity<ApiResponse> verifyEmail(@RequestParam String token) {
    userService.completeSignup(token);
    return ResponseEntity.ok(ApiResponse.success("회원가입이 완료되었습니다"));
  }

  @GetMapping("/me")
  public ResponseEntity<ApiResponse<TempUserInfoResponse>> getCurrentUser(
      @AuthenticationPrincipal UserDetails userDetails) {

    TempUserInfoResponse userInfo = userService.getCurrentUser(userDetails.getUsername());
    return ResponseEntity.ok()
        .body(ApiResponse.success(userInfo, "사용자 정보를 성공적으로 가져왔습니다."));
  }

  // Exception Handlers
  // 403
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ApiResponse<Void>> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.error("인증에 실패했습니다: " + e.getMessage()));
  }

  // 400
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ApiResponse.error(e.getMessage()));
  }

  // 500
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiResponse.error("서버 오류가 발생했습니다."));
  }
}
