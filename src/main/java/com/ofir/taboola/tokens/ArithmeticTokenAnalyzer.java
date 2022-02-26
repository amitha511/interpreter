package com.ofir.taboola.tokens;

public class ArithmeticTokenAnalyzer extends AbstractTokenAnalyzer {

    public ArithmeticTokenAnalyzer(){
        super();
    }


    public boolean isAdd(String token){
        return token.equals("+");
    }

    public boolean isSub(String token){
        return token.equals("-");
    }

    public boolean isMul(String token){
        return token.equals("*");
    }

    public boolean isIncrement(String token){
        return token.equals("++");
    }
}
