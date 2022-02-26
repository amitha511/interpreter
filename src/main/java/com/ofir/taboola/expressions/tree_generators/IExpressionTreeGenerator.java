package com.ofir.taboola.expressions.tree_generators;

import com.ofir.taboola.expressions.models.ExpressionTree;

public interface IExpressionTreeGenerator {
    ExpressionTree generateTree(String[] tokens);
}
