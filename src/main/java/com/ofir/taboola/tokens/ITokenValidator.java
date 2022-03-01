package com.ofir.taboola.tokens;

import com.ofir.taboola.exceptions.InvalidTokenException;

import java.util.List;
import java.util.Map;

public interface ITokenValidator {
    List<InvalidTokenException> validate(String token, AbstractTokenAnalyzer ta,
                                         Map<String, Integer> variablesState);
}
