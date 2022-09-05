package com.techgeeknext.publisher;

import com.techgeeknext.event.EmailEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EmailPublisher {

    private final ApplicationEventPublisher eventPublisher;

    EmailPublisher(ApplicationEventPublisher publisher) {
        this.eventPublisher = publisher;
    }

    public void publishEmailEvent(EmailEvent event) {
        eventPublisher.publishEvent(event);
    }

    public void publishMsgEvent(String msg) {
        eventPublisher.publishEvent(msg);
    }
}
