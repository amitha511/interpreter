import analyzers.SingleCommandExecutor;
import analyzers.IntegerExpressionAnalyser;
import analyzers.ICommandExecutor;

import java.util.HashMap;
import java.util.Map;

public class Executor {
    Map<String,Integer> varsState;
    ICommandExecutor commandExecutor;
    public Executor(){
        varsState = new HashMap<>();
        commandExecutor = new SingleCommandExecutor(new IntegerExpressionAnalyser());
    }

    /*
        This method changes varsState based on a given expression
        The method has several assumptions:
            1. The expression is a string where tokens are always separated by a space
            2. Numbers can not be negative in the expression
            3. The expression is valid
     */
    public void calculate(String expr) {
        commandExecutor.execute(expr, varsState);
    }
}
