package com.gmail.bergrin.SpringSecurytyApp.util;

import com.gmail.bergrin.SpringSecurytyApp.models.Person;
import com.gmail.bergrin.SpringSecurytyApp.services.PersonDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

  private final PersonDetailsService personDetailsService;

  public PersonValidator(PersonDetailsService personDetailsService) {
    this.personDetailsService = personDetailsService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Person.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Person person = (Person) target;
    try {
      personDetailsService.loadUserByUsername(person.getUsername());
    } catch (UsernameNotFoundException ignored) {
      return;
    }

    errors.rejectValue("username", "ERROR CODE", "Username not found");
  }
}
