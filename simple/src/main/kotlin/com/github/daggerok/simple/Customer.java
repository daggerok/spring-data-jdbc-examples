package com.github.daggerok.simple;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

//// minimal accepted lombok config:
//@Getter
//@Wither
//@ToString
//@AllArgsConstructor
//@EqualsAndHashCode(exclude = "id")

@Value
@Wither
@EqualsAndHashCode(exclude = "id")
class Customer {

  @Id
  Long id;
  String name;
  LocalDate date;
  Gender gender;

  public static Customer createForName(String name) {
    return new Customer(null, name, LocalDate.now(), null);
  }

//  // See lombok annotation: @Wither
//  // this method will be using on repository.save() execution
//  public Customer withId(Long id) {
//    return new Customer(id, name, date, gender);
//  }

//  //// See lombok annotation: @AllArgsConstructor
//  // it's always good for performance to have single constructor for all fields
//  Customer(Long id, String name, LocalDate date, Gender gender) {
//    this.id = id;
//    this.name = name;
//    this.date = date;
//    this.gender = gender;
//  }
}
