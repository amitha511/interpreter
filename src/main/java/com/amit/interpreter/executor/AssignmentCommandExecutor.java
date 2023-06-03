package com.amit.interpreter.executor;

import com.amit.interpreter.expressions.ExpressionTree;
import com.amit.interpreter.expressions.tree_generators.IExpressionTreeGenerator;
import com.amit.interpreter.expressions.tree_generators.ArithmeticExpressionTreeGenerator;
import com.amit.interpreter.tokens.ArithmeticTokenAnalyzer;

import java.util.Arrays;
import java.util.Map;

public class AssignmentCommandExecutor implements ICommandExecutor {

    IExpressionTreeGenerator expressionTreeGenerator;
    ArithmeticTokenAnalyzer tokenAnalyzer;

    public AssignmentCommandExecutor(){
        this.expressionTreeGenerator = new ArithmeticExpressionTreeGenerator();
        this.tokenAnalyzer = new ArithmeticTokenAnalyzer();
    }

    @Override
    public void execute(String command, Map<String,Integer> varsState) {
        String[] cmdTokens = command.split(" ");
        String dstVar = cmdTokens[0];
        String assignmentOperator = cmdTokens[1];
        String[] exprTokens = Arrays.copyOfRange(cmdTokens,2, cmdTokens.length);

        ExpressionTree tree = buildExpressionTree(exprTokens, assignmentOperator, dstVar);

        int val = evaluateExpressionTree(tree, varsState);

        varsState.put(dstVar, val);
    }

    private ExpressionTree buildExpressionTree(String[] tokens, String assignmentOperator, String dst) {
        ExpressionTree tree = expressionTreeGenerator.generateTree(tokens);
        if(assignmentOperator.equals("+=")){
            ExpressionTree parent = new ExpressionTree("+");
            parent.left = new ExpressionTree(dst);
            parent.right = tree;
            tree = parent;
        }
        return tree;
    }

    private int evaluateExpressionTree(ExpressionTree exprTree, Map<String,Integer> varsState) {
        if(exprTree == null) return 0;
        String token = exprTree.getVal();
        //++i or i++
        if(tokenAnalyzer.isIncrement(token)){
            String varToIncrement;
            int evaluated;
            //++i scenario
            if(exprTree.left == null){
                varToIncrement = exprTree.right.getVal();
                evaluated = varsState.get(varToIncrement) + 1;
            }
            //i++ scenario
            else{
                varToIncrement = exprTree.left.getVal();
                evaluated = varsState.get(varToIncrement);
            }
            varsState.put(varToIncrement, varsState.get(varToIncrement) +1);
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
}
