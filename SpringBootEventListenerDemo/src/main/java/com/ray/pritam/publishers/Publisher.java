package com.ray.pritam.publishers;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.ray.pritam.events.UserCreatedEvent;
import com.ray.pritam.events.UserRemovedEvent;

@Component
public class Publisher {

  private final ApplicationEventPublisher publisher;

  public Publisher(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  public void publishEvent() {
    // Async Event
    publisher.publishEvent("I'm Async");

    // @EventListener Annotated and ApplicationListener
    publisher.publishEvent(new UserCreatedEvent(this, "Lucario"));
    publisher.publishEvent(new UserRemovedEvent("Lucario"));

    // Conditional Listener
    publisher.publishEvent(new UserCreatedEvent(this, "reflectoring"));
    publisher.publishEvent(new UserRemovedEvent("reflectoring"));

  }
}
