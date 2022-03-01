package com.ofir.taboola.exceptions;

public class ErrorMessages {
    public static final String invalidTokenMessage(String token){
        return "Invalid token " + token;
    }

    public static final String variableNotDeclared(String token){
        return "variable " + token + " was not declared";
    }
}
