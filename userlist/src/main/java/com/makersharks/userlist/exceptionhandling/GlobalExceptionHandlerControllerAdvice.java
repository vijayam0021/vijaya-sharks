package com.makersharks.userlist.exceptionhandling;

import com.makersharks.userlist.Utils.ApplicationConstants;
import com.makersharks.userlist.Utils.BizErrorResponse;
import com.makersharks.userlist.exceptions.EntityNotFoundException;
import com.makersharks.userlist.exceptions.GenericBizException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<BizErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        BizErrorResponse error = new BizErrorResponse(ApplicationConstants.ENTITY_NOT_FOUND, ex.getDetails(), ex.getStatus());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {GenericBizException.class})
    public ResponseEntity<BizErrorResponse> handleGenericBizExceptionWithMessage(GenericBizException ex) {
        BizErrorResponse error = new BizErrorResponse(ApplicationConstants.ERROR, ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<BizErrorResponse> handleException(Exception ex) {
        BizErrorResponse error = new BizErrorResponse(ApplicationConstants.ERROR, ex.getMessage(), "Error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
