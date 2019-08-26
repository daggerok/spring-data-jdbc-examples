package com.github.daggerok.simple.ref;

import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@Table("Book_Author")
public class AuthorRef {
  Long author;
}
