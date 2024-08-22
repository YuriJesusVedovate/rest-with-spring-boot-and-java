package br.com.yuri.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class ExceptionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public final String message;
    public final Date timestamp = new Date();
    public final String details;

    public ExceptionResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }
}
