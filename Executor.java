import command_executors.AssignmentCommandExecutor;
import expression_analyzers.ArithmeticExpressionAnalyser;
import command_executors.ICommandExecutor;

import java.util.HashMap;
import java.util.Map;

public class Executor {
    Map<String,Integer> varsState;
    ICommandExecutor commandExecutor;
    public Executor(){
        varsState = new HashMap<>();
        commandExecutor = new AssignmentCommandExecutor();
    }

    /*
        This method changes varsState based on a given expression
        The method has several assumptions:
            1. The expression is a string where tokens are always separated by a space
            2. Integer can not be equal to Integer.MIN_VALUE in expressions
            3. The expression is valid and always kind of assignment operation
     */
    public void calculate(String expr) {
        commandExecutor.execute(expr, varsState);
    }
}
