package com.amit.interpreter.expressions.tree_generators;

import com.amit.interpreter.expressions.ExpressionTree;

/*
    Arithmetic expression is an expression that can always be evaluated to a single integer
    The class analyzes arithmetic expressions and generates an expression tree
 */
public class ArithmeticExpressionTreeGenerator implements IExpressionTreeGenerator {

    @Override
    public ExpressionTree generateTree(String[] tokens) {
        return generateTree(tokens, 0, tokens.length-1);
    }

    private ExpressionTree generateTree(String[] tokens, int start, int end) {
        if(start < 0 || end >= tokens.length || start > end){
            return null;
        }
        ExpressionTree res;
        if(start == end){
            return generateLeaf(tokens[start]);
        }

        for(int i=start; i <= end; i++){
            String token = tokens[i];
            if(token.equals("+") || token.equals("-")){
                res = new ExpressionTree(token);
                res.left = generateTree(tokens, start, i-1);
                res.right = generateTree(tokens , i+1 ,end);
                return res;
            }
        }

        for(int i=start; i <= end; i++){
            String token = tokens[i];
            if(token.equals("*")){
                res = new ExpressionTree(token);
                res.left = generateTree(tokens, start, i-1);
                res.right = generateTree(tokens , i+1 ,end);
                return res;
            }
        }
        return null;
    }

    private ExpressionTree generateLeaf(String leaf) {
        ExpressionTree toReturn;
        if(leaf.startsWith("++")){
            toReturn = new ExpressionTree("++");
            String varExcludingPlusPlus = leaf.substring(2);
            toReturn.right = new ExpressionTree(leaf.substring(2));
        } else if(leaf.endsWith("++")){
            toReturn = new ExpressionTree("++");
            String varExcludingPlusPlus = leaf.substring(0,leaf.length()-2);
            toReturn.left = new ExpressionTree(varExcludingPlusPlus);
        } else{
            toReturn = new ExpressionTree(leaf);
        }
        return toReturn;
    }
}
