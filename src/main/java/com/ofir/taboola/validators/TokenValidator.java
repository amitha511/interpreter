package com.ofir.taboola.validators;

import com.ofir.taboola.exceptions.InvalidTokenException;
import com.ofir.taboola.tokens.AbstractTokenAnalyzer;
import com.ofir.taboola.tokens.ArithmeticTokenAnalyzer;

import java.util.List;
import java.util.Map;

public class TokenValidator {
    private Map<String, Integer> state;
    AbstractTokenAnalyzer at;
    ITokenValidator tokenValidator;

    public TokenValidator(Map<String, Integer> state){
        this.at = new ArithmeticTokenAnalyzer();
        this.tokenValidator = new AssignmentTokenValidator();
        this.state = state;
    }

    public void validate(String token) throws InvalidTokenException {
        List<InvalidTokenException> errors = this.tokenValidator.validate(token, at , state);
        if(!errors.isEmpty()){
            throw errors.get(0);
        }
    }
}
