package com.amit.interpreter.expressions.tree_generators;

import com.amit.interpreter.exceptions.InvalidTokenException;
import com.amit.interpreter.expressions.ExpressionTree;
import com.amit.interpreter.validators.TokenValidator;

public interface IExpressionTreeGenerator {
    ExpressionTree generateTree(String[] tokens, TokenValidator tokenValidator) throws InvalidTokenException;
}
