package com.github.daggerok.ddd.domain;

import org.springframework.data.annotation.Id;

class Gender {

  @Id
  Long id;
  String whoCares;
}
