package com.workingsession.advice;

import com.workingsession.exception.DataAlreadyExist;
import com.workingsession.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdviceExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(DataNotFoundException.class)
    public Map<String,String> handleException(DataNotFoundException ex){
        Map<String,String> error=new HashMap<>();
        error.put("error message",ex.getMessage());
        return error;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAlreadyExist.class)
    public Map<String,String> handleDataExistException(DataAlreadyExist ex){
        Map<String,String> error=new HashMap<>();
        error.put("error message",ex.getMessage());
        return error;
    }

}
