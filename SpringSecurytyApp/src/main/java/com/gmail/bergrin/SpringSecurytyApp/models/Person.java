package com.gmail.bergrin.SpringSecurytyApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotEmpty(message = "username should not be empty")
  @Size(min = 2, max = 100, message = "username lengths should be from 2 to 100 symbols")
  @Column(name = "username")
  private String username;
  @Min(value = 1900, message = "year of birth should be greater than 1900")
  @Column(name = "year_of_birth")
  private int yearOfBirth;
  @Column(name = "password")
  private String password;

  public Person(String username, int yearOfBirth) {
    this.username = username;
    this.yearOfBirth = yearOfBirth;
  }
}
