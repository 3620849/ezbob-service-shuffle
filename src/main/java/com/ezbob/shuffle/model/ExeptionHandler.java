package com.ezbob.shuffle.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExeptionHandler extends ResponseEntityExceptionHandler{
        @ExceptionHandler(ConstraintViolationException.class)
        public final ResponseEntity<Object> handleRecordNotFoundException(ConstraintViolationException ex, WebRequest request) {

            return new ResponseEntity(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
}

