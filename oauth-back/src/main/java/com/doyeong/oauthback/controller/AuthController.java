package com.doyeong.oauthback.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doyeong.oauthback.dto.request.auth.SignUpRequestDto;
import com.doyeong.oauthback.dto.response.auth.SignUpResponseDto;
import com.doyeong.oauthback.service.AuthService;

import lombok.RequiredArgsConstructor;

// @ : 어노테이션
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
  
  private final AuthService authService;

  @PostMapping("/sign-up")
  // <> : 제너릭
  public ResponseEntity<? super SignUpResponseDto> signUp(
    @RequestBody @Valid SignUpRequestDto requestBody
  ) {
      ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
      return response;
  }

}
