package com.github.daggerok.ddd.events;

import org.springframework.context.ApplicationEvent;

public class CustomerCreatedEvent extends ApplicationEvent {
  public CustomerCreatedEvent(Object source) {
    super(source);
  }
}
