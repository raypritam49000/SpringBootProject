package com.techgeeknext.event;

public class EmailEvent {
    private String message;

    public EmailEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
