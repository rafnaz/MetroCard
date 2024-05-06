package com.geektrust.backend.exceptions;

public class InValidAmountException extends RuntimeException{
    
    public InValidAmountException(String msg){
        super(msg);
    }

    public InValidAmountException(){
        super();
    }
}
