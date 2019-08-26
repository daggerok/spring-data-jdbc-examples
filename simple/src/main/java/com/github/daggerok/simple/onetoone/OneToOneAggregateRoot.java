package com.github.daggerok.simple.onetoone;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
public class OneToOneAggregateRoot {
  @Id
  Long id;

  Set<OneToOneSetRef> names;

  public OneToOneAggregateRoot withName(String name) {
    OneToOneSetRef oneToOneSetRef = new OneToOneSetRef();
    oneToOneSetRef.name = name;

    if (Objects.isNull(names))
      names = new HashSet<>();

    names.add(oneToOneSetRef);
    return this;
  }
}
