package com.gmail.bergrin.SpringSecurytyApp.security;

import java.util.Collection;

import com.gmail.bergrin.SpringSecurytyApp.models.Person;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class PersonDetails implements UserDetails {

  private final Person person;

  public PersonDetails(Person person) {
    this.person = person;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.person.getPassword();
  }

  @Override
  public String getUsername() {
    return this.person.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
