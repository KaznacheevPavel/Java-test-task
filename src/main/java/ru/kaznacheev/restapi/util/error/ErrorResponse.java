package ru.kaznacheev.restapi.util.error;

import java.sql.Timestamp;

public class ErrorResponse {
    private String message;
    private Timestamp timestamp;

    public ErrorResponse(String message, Timestamp timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
