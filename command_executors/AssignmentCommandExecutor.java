package command_executors;

import expression_analyzers.ArithmeticExpressionAnalyser;
import expression_analyzers.IExpressionAnalyzer;
import expression_models.ExpressionTree;
import token_analyzer.ArithmeticTokenAnalyzer;

import java.util.Arrays;
import java.util.Map;

public class AssignmentCommandExecutor implements ICommandExecutor {

    IExpressionAnalyzer expressionAnalyzer;
    ArithmeticTokenAnalyzer tokenAnalyzer;

    public AssignmentCommandExecutor(){
        this.expressionAnalyzer = new ArithmeticExpressionAnalyser();
        this.tokenAnalyzer = new ArithmeticTokenAnalyzer();
    }

    @Override
    public void execute(String command, Map<String,Integer> varsState) {
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
            System.err.println("bad expression !");
        }
        return tree;
    }

    private int evaluateExpressionTree(ExpressionTree exprTree, Map<String,Integer> varsState){
        if(exprTree == null) return 0;
        String nodeVal = exprTree.getVal();
        if(tokenAnalyzer.isIncrement(nodeVal)){
            String varName;
            int evaluated;
            if(exprTree.left == null){  //++i scenario
                varName = exprTree.right.getVal();
                evaluated = varsState.get(varName) + 1;
            }
            else{  //i++ scenario
                varName = exprTree.left.getVal();
                evaluated = varsState.get(varName);
            }
            varsState.put(varName, varsState.get(varName) +1);
            return evaluated;
        }
        if(tokenAnalyzer.isInteger(nodeVal)) {
            return Integer.valueOf(nodeVal);
        }
        if(tokenAnalyzer.isVariable(nodeVal)){
            return varsState.get(nodeVal);
        }
        if(tokenAnalyzer.isAdd(nodeVal)){
            return evaluateExpressionTree(exprTree.left, varsState)
                    + evaluateExpressionTree(exprTree.right, varsState);
        }
        if(tokenAnalyzer.isSub(nodeVal)){
            return evaluateExpressionTree(exprTree.left, varsState)
                    - evaluateExpressionTree(exprTree.right, varsState);
        }
        if(tokenAnalyzer.isMul(nodeVal)){
            return evaluateExpressionTree(exprTree.left, varsState)
                    * evaluateExpressionTree(exprTree.right, varsState);
        }
        return 0;
    }
}
