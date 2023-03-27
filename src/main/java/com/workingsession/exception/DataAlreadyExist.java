package com.workingsession.exception;

public class DataAlreadyExist extends RuntimeException{
    public DataAlreadyExist(String message){
        super(message);
    }
}
