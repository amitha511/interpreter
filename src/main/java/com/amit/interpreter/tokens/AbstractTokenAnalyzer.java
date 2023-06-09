package com.amit.interpreter.tokens;

public abstract class AbstractTokenAnalyzer {

    public boolean isVariable(String token){
        if(token.isEmpty()) return false;
        for(int i=0; i < token.length(); i++){
            char c = token.charAt(i);
            boolean isAlphabetic = Character.isAlphabetic(c);
            if(i == 0 && !isAlphabetic){
                return false;
            }
            if(!isAlphabetic && c != '_' && c != '$' && !Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    public boolean isInteger(String token) {
        try {
            Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


}
