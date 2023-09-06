package com.doyeong.oauthback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doyeong.oauthback.Repository.UserRepository;
import com.doyeong.oauthback.dto.request.auth.SignUpRequestDto;
import com.doyeong.oauthback.dto.response.ResponseDto;
import com.doyeong.oauthback.dto.response.auth.SignUpResponseDto;
import com.doyeong.oauthback.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 외부주입 가능
public class AuthServiceImplement implements AuthService {
  
  private final UserRepository userRepository;

  @Override
  public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
    
    SignUpResponseDto result = null;
    
    String id = dto.getId();
    
    try {
      
      boolean hasId = userRepository.existsById(id);
      if (hasId) return SignUpResponseDto.existedId();
      
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return SignUpResponseDto.success();

  }
  
}
