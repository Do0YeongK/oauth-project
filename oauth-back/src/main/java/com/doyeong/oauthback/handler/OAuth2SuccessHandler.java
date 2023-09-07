package com.doyeong.oauthback.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.doyeong.oauthback.entity.ApplicationOAuth2User;
import com.doyeong.oauthback.provider.JwtProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  
  private final JwtProvider jwtProvider;

  @Override
  public void onAuthenticationSuccess(
    HttpServletRequest request, 
    HttpServletResponse response, 
    Authentication authentication
  ) throws IOException {

    // 로그인 결과(token) 만들 것
    ApplicationOAuth2User oAuth2User = (ApplicationOAuth2User) authentication.getPrincipal();
    boolean isSignUp = oAuth2User.isSignUp();
    if (!isSignUp) response.sendRedirect("http://localhost:3000/oauth2/signup/");
    String id = oAuth2User.getName();
    String token = jwtProvider.create(id);

    // response.getWriter().write(token);
    response.sendRedirect("http://localhost:3000/oauth2/" + token);   //sendRedirect : get 요청만 할 수 있음

  }


}
