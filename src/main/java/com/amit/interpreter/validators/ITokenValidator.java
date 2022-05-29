package com.amit.interpreter.validators;

import com.amit.interpreter.exceptions.InvalidTokenException;
import com.amit.interpreter.tokens.AbstractTokenAnalyzer;

import java.util.List;
import java.util.Map;

public interface ITokenValidator {
    List<InvalidTokenException> validate(String token, AbstractTokenAnalyzer ta,
                                         Map<String, Integer> variablesState);
}
