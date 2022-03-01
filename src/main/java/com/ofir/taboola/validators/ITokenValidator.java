package com.ofir.taboola.validators;

import com.ofir.taboola.exceptions.InvalidTokenException;
import com.ofir.taboola.tokens.AbstractTokenAnalyzer;

import java.util.List;
import java.util.Map;

public interface ITokenValidator {
    List<InvalidTokenException> validate(String token, AbstractTokenAnalyzer ta,
                                         Map<String, Integer> variablesState);
}
