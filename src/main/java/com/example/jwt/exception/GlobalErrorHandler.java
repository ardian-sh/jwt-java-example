package com.example.jwt.exception;


import com.example.jwt.enumeration.MessageResponseEnum;
import com.example.jwt.response.CustomResponse;
import com.example.jwt.util.CustomResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    protected <T> ResponseEntity<CustomResponse<T>> handelException(Exception ex, WebRequest request) {
        log.error(((ServletWebRequest) request).getRequest().getRequestURI());
        log.error(ex.getClass().getSimpleName());
        log.error(ex.getMessage());

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return CustomResponseUtil.error(MessageResponseEnum.METHOD_NOT_MATCH, HttpStatus.METHOD_NOT_ALLOWED);
        }

        if (ex instanceof HttpMediaTypeNotSupportedException) {
            return CustomResponseUtil.error(MessageResponseEnum.CONTENT_TYPE_NOT_ACCEPTABLE, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return CustomResponseUtil.error(MessageResponseEnum.CONTENT_TYPE_NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE);
        }

        if (ex instanceof MissingServletRequestParameterException) {
            return CustomResponseUtil.error(MessageResponseEnum.MISSING_PARAM_REQUEST, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof ServletRequestBindingException) {
            return CustomResponseUtil.error(MessageResponseEnum.MISSING_HEADER, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof TypeMismatchException) {
            return CustomResponseUtil.error(MessageResponseEnum.CONTENT_TYPE_NOT_ACCEPTABLE, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof HttpMessageNotReadableException) {
            return CustomResponseUtil.error(MessageResponseEnum.MESSAGE_NOT_READABLE, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof HttpMessageNotWritableException) {
            return CustomResponseUtil.error(MessageResponseEnum.MESSAGE_NOT_WRITEABLE, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof MethodArgumentNotValidException) {
            return CustomResponseUtil.error(MessageResponseEnum.METHOD_MOT_MATCH, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof MissingServletRequestPartException) {
            return CustomResponseUtil.error(MessageResponseEnum.INVALID_PARAMETER, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof BindException) {
            return CustomResponseUtil.error(MessageResponseEnum.ERROR_HANDLE_BIND, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof NullPointerException) {
            return CustomResponseUtil.error(MessageResponseEnum.NULL_POINTER, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof NoSuchElementException) {
            return CustomResponseUtil.error(MessageResponseEnum.ELEMENT_NOT_FOUND, HttpStatus.NO_CONTENT);
        }

        if (ex instanceof IllegalStateException) {
            return CustomResponseUtil.error(MessageResponseEnum.INVALID_JSON_FORMAT, HttpStatus.NO_CONTENT);
        }

        if (ex instanceof NumberFormatException) {
            return CustomResponseUtil.error(MessageResponseEnum.CONVERTING_NOT_MATCH, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof UsernameNotFoundException) {
            return CustomResponseUtil.error(MessageResponseEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        if (ex instanceof BadCredentialsException) {
            return CustomResponseUtil.error(MessageResponseEnum.USER_NOT_MATCH, HttpStatus.UNAUTHORIZED);
        }

        if (ex instanceof AccountStatusException) {
            return CustomResponseUtil.error(MessageResponseEnum.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof AccessDeniedException) {
            return CustomResponseUtil.error(MessageResponseEnum.ACCESS_DENIED, HttpStatus.FORBIDDEN);
        }

        if (ex instanceof SignatureException) {
            return CustomResponseUtil.error(MessageResponseEnum.JWT_INVALID, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof ExpiredJwtException) {
            return CustomResponseUtil.error(MessageResponseEnum.TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof InsufficientAuthenticationException) {
            return CustomResponseUtil.error(MessageResponseEnum.AUTHENTICATED_REQUIRED, HttpStatus.FORBIDDEN);
        }

        return CustomResponseUtil.error(MessageResponseEnum.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomValidationException.class)
    protected <T> ResponseEntity<CustomResponse<T>> handelCustomException(CustomValidationException ce, WebRequest request) {
        log.error(((ServletWebRequest) request).getRequest().getRequestURI());
        log.error(ce.getClass().getSimpleName());
        log.error(ce.getMessageResponse().toString());

        return CustomResponseUtil.error(ce.getMessageResponse(), ce.getHttpStatus());
    }

}
