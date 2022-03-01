package com.ofir.taboola.validators;

import com.ofir.taboola.exceptions.ErrorMessages;
import com.ofir.taboola.exceptions.InvalidTokenException;
import com.ofir.taboola.tokens.AbstractTokenAnalyzer;

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
        boolean increment = isIncrement(token, tokenAnalyzer);
        if(isVariable && !isDeclaredVariable){
            exceptions.add(new InvalidTokenException(ErrorMessages.variableNotDeclared(token)));
        } else if(!isInteger && !isVariable && !isArithmeticOperator && !increment){
            exceptions.add(new InvalidTokenException(ErrorMessages.invalidTokenMessage(token)));
        }
        return exceptions;
    }

    public boolean isIncrement(String token, AbstractTokenAnalyzer ta){
        boolean preIncrement = token.startsWith("++") && ta.isVariable(token.substring(2));
        boolean postIncrement = token.endsWith("++") && ta.isVariable(token.substring(0, token.length()-2));
        return preIncrement || postIncrement;
    }
}
