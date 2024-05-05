package com.example.jwt.response;

import com.example.jwt.enumeration.MessageResponseEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomResponse<T> {
    private boolean success;
    @JsonIgnore
    private MessageResponseEnum message;
    private T data;
    @Setter(AccessLevel.NONE)
    @JsonProperty(value = "message")
    private String messageToString;

    public String getMessageToString() {
        return this.message.toString();
    }
}
