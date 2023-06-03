package com.amit.interpreter.expressions.tree_generators;

import com.amit.interpreter.expressions.ExpressionTree;

public interface IExpressionTreeGenerator {
    ExpressionTree generateTree(String[] tokens);
}
