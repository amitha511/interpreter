package expressions.tree_generators;

import expressions.models.ExpressionTree;

public interface IExpressionTreeGenerator {
    ExpressionTree generateTree(String[] tokens);
}
