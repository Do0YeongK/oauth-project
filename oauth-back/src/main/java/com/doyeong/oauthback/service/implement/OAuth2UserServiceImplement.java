package com.doyeong.oauthback.service.implement;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.doyeong.oauthback.Repository.UserRepository;
import com.doyeong.oauthback.entity.ApplicationOAuth2User;
import com.doyeong.oauthback.entity.UserEntity;
// import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {
  
  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

    // Override하기 위해 super 결과 호출 받아 옴(다 적기 힘들어서)
    OAuth2User oAuth2User = super.loadUser(request);

    // 정보 확인 할려고 출력했지만 실제로는 이렇게 정보를 보여주면 안됌!!
    // try {
    //   System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));  
    // } catch (Exception exception) {
    //   exception.printStackTrace();
    // }

    String id = (String) oAuth2User.getAttributes().get("login"); // Object -> String 다운캐스팅
    String profileImage = (String) oAuth2User.getAttributes().get("avatar_url"); // Object -> String 다운캐스팅

    boolean existedId = userRepository.existsById(id);
    // if (!existedId) {
    //   UserEntity userEntity = new UserEntity(id, profileImage);
    //   userRepository.save(userEntity);
    // }

    if (!existedId) return new ApplicationOAuth2User(id, false, oAuth2User.getAttributes());
    

    return new ApplicationOAuth2User(id, true, oAuth2User.getAttributes());

  }
  
}
