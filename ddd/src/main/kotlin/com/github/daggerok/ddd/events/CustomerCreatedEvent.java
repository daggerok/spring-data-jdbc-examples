package com.github.daggerok.ddd.events;

import org.springframework.context.ApplicationEvent;

public class CustomerCreatedEvent extends ApplicationEvent {

  /**
   * Create a new ApplicationEvent.
   *
   * @param source the object on which the event initially occurred (never {@code null})
   */
  public CustomerCreatedEvent(Object source) {
    super(source);
  }
}
