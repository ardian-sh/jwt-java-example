package com.example.jwt.exception;

import com.example.jwt.enumeration.MessageResponseEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class CustomValidationException extends RuntimeException {
    private MessageResponseEnum messageResponse;
    private HttpStatus httpStatus;

    public CustomValidationException(MessageResponseEnum messageResponse, HttpStatus httpStatus) {
        this.messageResponse = messageResponse;
        this.httpStatus = httpStatus;
    }

    public CustomValidationException(String message, MessageResponseEnum messageResponse, HttpStatus httpStatus) {
        super(message);
        this.messageResponse = messageResponse;
        this.httpStatus = httpStatus;
    }

    public CustomValidationException(String message, Throwable cause, MessageResponseEnum messageResponse, HttpStatus httpStatus) {
        super(message, cause);
        this.messageResponse = messageResponse;
        this.httpStatus = httpStatus;
    }

    public CustomValidationException(Throwable cause, MessageResponseEnum messageResponse, HttpStatus httpStatus) {
        super(cause);
        this.messageResponse = messageResponse;
        this.httpStatus = httpStatus;
    }

    public CustomValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, MessageResponseEnum messageResponse, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.messageResponse = messageResponse;
        this.httpStatus = httpStatus;
    }
}
