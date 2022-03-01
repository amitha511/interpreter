package com.ofir.taboola.expressions.tree_generators;

import com.ofir.taboola.exceptions.InvalidTokenException;
import com.ofir.taboola.expressions.ExpressionTree;
import com.ofir.taboola.validators.TokenValidator;

public interface IExpressionTreeGenerator {
    ExpressionTree generateTree(String[] tokens, TokenValidator tokenValidator) throws InvalidTokenException;
}
