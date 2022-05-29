package com.amit.interpreter.exceptions;

public class InvalidTokenException extends InvalidCommandException {
    public InvalidTokenException(String message){
        super(message);
    }
}
