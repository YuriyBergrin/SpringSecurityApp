package com.gmail.bergrin.SpringSecurytyApp.config;

import java.util.Collections;

import com.gmail.bergrin.SpringSecurytyApp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

  private final PersonDetailsService personDetailsService;

  @Autowired
  public AuthProviderImpl(PersonDetailsService personDetailsService) {
    this.personDetailsService = personDetailsService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    UserDetails personDetails = personDetailsService.loadUserByUsername(username);
    String password = authentication.getCredentials().toString();
    if (!password.equals(personDetails.getPassword())) {
      throw new BadCredentialsException("Incorrect password");
    }
    return new PreAuthenticatedAuthenticationToken(personDetails, password, Collections.emptyList());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return true;
  }
}
