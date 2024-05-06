package com.geektrust.backend.exceptions;

public class InvalidStationException extends RuntimeException{
    public InvalidStationException(String msg){
        super(msg);
    }

    public InvalidStationException(){
        super();
    }
}
