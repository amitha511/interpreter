package com.ofir.taboola.tokens;

import com.ofir.taboola.exceptions.InvalidTokenException;

import java.util.*;

public class AssignmentTokenValidator implements ITokenValidator {

    private final Set<String> tokens;

    public AssignmentTokenValidator(){
        tokens = new HashSet<>(Arrays.asList("+" , "-" , "*" , "++"));
    }

    @Override
    public List<InvalidTokenException> validate(String token, AbstractTokenAnalyzer tokenAnalyzer,
                                               Map<String, Integer> variablesState) {
        List<InvalidTokenException> exceptions = new ArrayList<>();
        boolean isArithmeticOperator = tokens.contains(token);
        boolean isDeclaredVariable = variablesState.containsKey(token);
        boolean isVariable = tokenAnalyzer.isVariable(token);
        boolean isInteger = tokenAnalyzer.isInteger(token);

        if(isVariable && !isDeclaredVariable){
            exceptions.add(new InvalidTokenException("variable " + token + " was not declared"));
        } else if(!isInteger && !isVariable && !isArithmeticOperator){
            exceptions.add(new InvalidTokenException("Invalid token " + token));
        }
        return exceptions;
    }
}
