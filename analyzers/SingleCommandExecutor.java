package analyzers;

import expression_models.ExpressionTree;

import java.util.Arrays;
import java.util.Map;

public class SingleCommandExecutor implements ICommandExecutor {

    IExpressionAnalyzer expressionAnalyzer;

    public SingleCommandExecutor(IExpressionAnalyzer expressionAnalyzer){
        this.expressionAnalyzer = expressionAnalyzer;
    }

    @Override
    public void execute(String command, Map<String,Integer> varsState) {
        String[] splitted = command.split(" ");
        String dst = splitted[0];
        String equationOperation = splitted[1];
        ExpressionTree tree = buildTree(Arrays.copyOfRange(splitted,
                2, splitted.length), equationOperation, dst);

        int res = evaluateExpressionTree(tree, varsState);
        varsState.put(dst, res);
    }

    private ExpressionTree buildTree(String[] tokens, String equationOperation, String dst){
        ExpressionTree tree = expressionAnalyzer.generateTree(tokens);
        if(equationOperation.equals("+=")){
            ExpressionTree parent = new ExpressionTree("+");
            parent.left = new ExpressionTree(dst);
            parent.right = tree;
            tree = parent;
        } else if(!equationOperation.equals("=")){
            System.err.println("bad command!!!!");
        }
        return tree;
    }

    private int evaluateExpressionTree(ExpressionTree exprTree, Map<String,Integer> varsState){
        if(exprTree == null) return 0;
        if(exprTree.getVal().equals("++")){
            String varName;
            int res;
            if(exprTree.left == null){  //++i
                varName = exprTree.right.getVal();
                res = varsState.get(varName) + 1;
            } else{ //i++
                varName = exprTree.left.getVal();
                res = varsState.get(varName);
            }
            varsState.put(varName, varsState.get(varName) +1);
            return res;
        }
        int number = tryParseInt(exprTree.getVal());
        if(number!= -1) {
            return number;
        } else if(Character.isAlphabetic(exprTree.getVal().charAt(0))){
            return varsState.get(exprTree.getVal());
        } else if(exprTree.getVal().equals("+")){
            return evaluateExpressionTree(exprTree.left, varsState)
                    + evaluateExpressionTree(exprTree.right, varsState);
        } else if(exprTree.getVal().equals("*")){
            return evaluateExpressionTree(exprTree.left, varsState)
                    * evaluateExpressionTree(exprTree.right, varsState);
        }
        return 0;
    }

    private int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
