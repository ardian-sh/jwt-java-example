package com.example.jwt.util;

import com.example.jwt.enumeration.MessageResponseEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.jwt.response.CustomResponse;

public class CustomResponseUtil {
    private CustomResponseUtil() {
    }

    public static <T> ResponseEntity<CustomResponse<T>> success(T data) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setSuccess(true);
        customResponse.setMessage(MessageResponseEnum.SUCCESS);
        customResponse.setData(data);

        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    public static <T> ResponseEntity<CustomResponse<T>> success() {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setSuccess(true);
        customResponse.setMessage(MessageResponseEnum.SUCCESS);
        customResponse.setData(null);

        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    public static <T> ResponseEntity<CustomResponse<T>> error(MessageResponseEnum message, HttpStatus httpStatus) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setSuccess(false);
        customResponse.setMessage(message);
        customResponse.setData(null);

        return new ResponseEntity<>(customResponse, httpStatus);
    }
}
