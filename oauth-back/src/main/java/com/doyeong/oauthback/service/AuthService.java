package com.doyeong.oauthback.service;

import org.springframework.http.ResponseEntity;

import com.doyeong.oauthback.dto.request.auth.SignUpRequestDto;
import com.doyeong.oauthback.dto.response.auth.SignUpResponseDto;

public interface AuthService {
  
  ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

}
