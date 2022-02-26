import commands.AssignmentCommandExecutor;
import commands.ICommandExecutor;

import java.util.HashMap;
import java.util.Map;

/*
    The class holds state of all the variables in the program
    assumptions:
        1. The commands in the program will always be kind of assignment command.
        2. The program state will store only integers without any other types.
 */
public class Executor {
    Map<String,Integer> state;
    ICommandExecutor commandExecutor;
    public Executor(){
        state = new HashMap<>();
        commandExecutor = new AssignmentCommandExecutor();
    }

    /*
        This method changes the state while executing the command
        The method has several assumptions:
            1. The command is presented as a string where tokens are always separated by a space
            2. Integer token can not be equal to Integer.MIN_VALUE.
     */
    public void execute(String command) {
        commandExecutor.execute(command, state);
    }
}
