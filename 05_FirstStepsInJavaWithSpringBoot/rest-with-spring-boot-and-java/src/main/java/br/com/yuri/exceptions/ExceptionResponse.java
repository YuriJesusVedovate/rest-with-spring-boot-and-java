package br.com.yuri.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private Date timestamp = new Date();
    private String details;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
