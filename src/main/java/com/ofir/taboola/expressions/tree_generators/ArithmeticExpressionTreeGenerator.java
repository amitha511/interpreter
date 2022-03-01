package com.ofir.taboola.expressions.tree_generators;

import com.ofir.taboola.exceptions.InvalidTokenException;
import com.ofir.taboola.expressions.ExpressionTree;
import com.ofir.taboola.validators.TokenValidator;

/*
    Arithmetic expression is an expression that can always be evaluated to a single integer
    The class analyzes arithmetic expressions and generates an expression tree
 */
public class ArithmeticExpressionTreeGenerator implements IExpressionTreeGenerator {

    @Override
    public ExpressionTree generateTree(String[] tokens, TokenValidator tokenValidator) throws InvalidTokenException {
        return generateTree(tokens, 0, tokens.length-1, tokenValidator);
    }

    private ExpressionTree generateTree(String[] tokens, int start, int end,
                                        TokenValidator tokenValidator) throws InvalidTokenException {
        if(start < 0 || end >= tokens.length || start > end){
            return null;
        }
        ExpressionTree res;
        if(start == end){
            tokenValidator.validate(tokens[start]);
            return generateLeaf(tokens[start], tokenValidator);
        }

        for(int i=start; i <= end; i++){
            String token = tokens[i];
            if(token.equals("+") || token.equals("-")){
                res = new ExpressionTree(token);
                res.left = generateTree(tokens, start, i-1, tokenValidator);
                res.right = generateTree(tokens , i+1 ,end, tokenValidator);
                return res;
            }
        }

        for(int i=start; i <= end; i++){
            String token = tokens[i];
            if(token.equals("*")){
                res = new ExpressionTree(token);
                res.left = generateTree(tokens, start, i-1, tokenValidator);
                res.right = generateTree(tokens , i+1 ,end, tokenValidator);
                return res;
            }
        }
        return null;
    }

    private ExpressionTree generateLeaf(String leaf, TokenValidator tokenValidator) throws InvalidTokenException {
        ExpressionTree toReturn;
        if(leaf.startsWith("++")){
            toReturn = new ExpressionTree("++");
            String varExcludingPlusPlus = leaf.substring(2);
            tokenValidator.validate(varExcludingPlusPlus);
            toReturn.right = new ExpressionTree(leaf.substring(2));
        } else if(leaf.endsWith("++")){
            toReturn = new ExpressionTree("++");
            String varExcludingPlusPlus = leaf.substring(0,leaf.length()-2);
            tokenValidator.validate(varExcludingPlusPlus);
            toReturn.left = new ExpressionTree(varExcludingPlusPlus);
        } else{
            toReturn = new ExpressionTree(leaf);
        }
        return toReturn;
    }
}
