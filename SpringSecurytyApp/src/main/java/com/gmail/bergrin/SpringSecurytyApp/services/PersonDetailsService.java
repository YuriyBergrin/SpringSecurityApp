package com.gmail.bergrin.SpringSecurytyApp.services;

import java.util.Optional;

import com.gmail.bergrin.SpringSecurytyApp.models.Person;
import com.gmail.bergrin.SpringSecurytyApp.repositories.PeopleRepository;
import com.gmail.bergrin.SpringSecurytyApp.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailsService implements UserDetailsService {

  private final PeopleRepository peopleRepository;

  @Autowired
  public PersonDetailsService(PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Person> person = peopleRepository.findByUsername(username);
    if (person.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    return new PersonDetails(person.get());
  }
}
