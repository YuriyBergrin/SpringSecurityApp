package com.gmail.bergrin.SpringSecurytyApp.repositories;

import java.util.Optional;

import com.gmail.bergrin.SpringSecurytyApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

  Optional<Person> findByUsername(String username);
}
