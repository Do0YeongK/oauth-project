package com.doyeong.oauthback.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class ApplicationOAuth2User implements OAuth2User {
  
  private String id;
  private boolean isSignUp;
  private Map<String, Object> attributes;
  private Collection<? extends GrantedAuthority> authorities;

  // 생성자
  public ApplicationOAuth2User(String id, boolean isSignUp, Map<String, Object> attributes) {
    this.id = id;
    this.isSignUp = isSignUp;
    this.attributes = attributes;
    this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  public boolean isSignUp() {
    return this.isSignUp;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return this.attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getName() {
    return this.id;
  }

}
