package com.github.daggerok.simple.ref;

import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
public class Author {
  @Id
  Long id;
}
