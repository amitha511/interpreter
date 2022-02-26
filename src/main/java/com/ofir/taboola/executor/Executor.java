package com.ofir.taboola.executor;

import com.ofir.taboola.commands.AssignmentCommandExecutor;
import com.ofir.taboola.commands.ICommandExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    The class holds state of all the variables in the program
    assumptions:
        1. The com.ofir.taboola.commands in the program will always be kind of assignment command.
        2. The program state will store only integers without any other types.
        3. All the com.ofir.taboola.commands are valid.
        4. The order of the com.ofir.taboola.commands is important.
 */
public class Executor {
    private Map<String,Integer> state;
    private ICommandExecutor commandExecutor;

    public Executor(){
        state = new HashMap<>();
        commandExecutor = new AssignmentCommandExecutor();
    }

    public void executeCommands(List<String> commands){
        for(String command : commands){
            execute(command);
        }
    }

    /*
        This method changes the state while executing the command
        The method has several assumptions:
            1. The command is presented as a string where com.ofir.taboola.tokens are always separated by a space
            2. Integer token can not be equal to Integer.MIN_VALUE.
     */
    private void execute(String command) {
        commandExecutor.execute(command, state);
    }

    public Map<String,Integer> getState(){
        return state;
    }
}
