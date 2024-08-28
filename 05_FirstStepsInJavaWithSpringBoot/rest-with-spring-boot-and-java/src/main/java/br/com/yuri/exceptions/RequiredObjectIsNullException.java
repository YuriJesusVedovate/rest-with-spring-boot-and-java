package br.com.yuri.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RequiredObjectIsNullException() {
        super("It is not allowed to pass a null object as a parameter.");
    }

    public RequiredObjectIsNullException(String message) {
        super(message);
    }

}
