package com.ofir.taboola.executor;

import com.ofir.taboola.exceptions.InvalidTokenException;
import com.ofir.taboola.expressions.tree_generators.ArithmeticExpressionTreeGenerator;
import com.ofir.taboola.expressions.tree_generators.IExpressionTreeGenerator;
import com.ofir.taboola.expressions.models.ExpressionTree;
import com.ofir.taboola.tokens.ArithmeticTokenAnalyzer;
import com.ofir.taboola.tokens.AssignmentTokenValidator;
import com.ofir.taboola.tokens.ITokenValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AssignmentCommandExecutor implements ICommandExecutor {

    IExpressionTreeGenerator expressionAnalyzer;
    ArithmeticTokenAnalyzer tokenAnalyzer;
    ITokenValidator tokenValidator;

    public AssignmentCommandExecutor(){
        this.expressionAnalyzer = new ArithmeticExpressionTreeGenerator();
        this.tokenAnalyzer = new ArithmeticTokenAnalyzer();
        this.tokenValidator = new AssignmentTokenValidator();
    }

    @Override
    public void execute(String command, Map<String,Integer> varsState) throws InvalidTokenException {
        String[] commandAsArr = command.split(" ");
        String dstVar = commandAsArr[0];
        String equationOperation = commandAsArr[1];
        String[] expression = Arrays.copyOfRange(commandAsArr,2, commandAsArr.length);
        ExpressionTree tree = buildTree(expression, equationOperation, dstVar);

        int exprEvaluated = evaluateExpressionTree(tree, varsState);

        varsState.put(dstVar, exprEvaluated);
    }

    private ExpressionTree buildTree(String[] tokens, String equationOperation, String dst){
        ExpressionTree tree = expressionAnalyzer.generateTree(tokens);
        if(equationOperation.equals("+=")){
            ExpressionTree parent = new ExpressionTree("+");
            parent.left = new ExpressionTree(dst);
            parent.right = tree;
            tree = parent;
        } else if(!equationOperation.equals("=")){
            System.err.println("not an assignment command !!");
        }
        return tree;
    }

    private int evaluateExpressionTree(ExpressionTree exprTree,
                                       Map<String,Integer> varsState) throws InvalidTokenException {
        if(exprTree == null) return 0;
        String token = exprTree.getVal();
        validate(token, varsState);
        //++i or i++
        if(tokenAnalyzer.isIncrement(token)){
            String variableToInc;
            int evaluated;
            //++i scenario
            if(exprTree.left == null){
                variableToInc = exprTree.right.getVal();
                validate(variableToInc, varsState);
                evaluated = varsState.get(variableToInc) + 1;
            }
            //i++ scenario
            else{
                variableToInc = exprTree.left.getVal();
                validate(variableToInc, varsState);
                evaluated = varsState.get(variableToInc);
            }
            varsState.put(variableToInc, varsState.get(variableToInc) +1);
            return evaluated;
        }
        if(tokenAnalyzer.isInteger(token)) {
            return Integer.parseInt(token);
        }
        if(tokenAnalyzer.isVariable(token)){
            return varsState.get(token);
        }
        if(tokenAnalyzer.isAdd(token)){
            return evaluateExpressionTree(exprTree.left, varsState)
                    + evaluateExpressionTree(exprTree.right, varsState);
        }
        if(tokenAnalyzer.isSub(token)){
            return evaluateExpressionTree(exprTree.left, varsState)
                    - evaluateExpressionTree(exprTree.right, varsState);
        }
        if(tokenAnalyzer.isMul(token)){
            return evaluateExpressionTree(exprTree.left, varsState)
                    * evaluateExpressionTree(exprTree.right, varsState);
        }
        return 0;
    }

    private void validate(String token, Map<String,Integer> state) throws InvalidTokenException {
        List<InvalidTokenException> errors = this.tokenValidator.validate(token, this.tokenAnalyzer, state);
        if(!errors.isEmpty()){
            throw errors.get(0);
        }
    }
}
