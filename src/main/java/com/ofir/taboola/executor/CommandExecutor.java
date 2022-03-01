package com.ofir.taboola.executor;

import com.ofir.taboola.exceptions.InvalidCommandException;
import com.ofir.taboola.exceptions.InvalidTokenException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/*
    The class holds state of all the variables in the program
    assumptions:
        1. The commands in the program will always be kind of assignment command.
        2. The program's state stores only integer variables.
        3. The order of the commands matters.
 */
public class CommandExecutor {
    private Map<String,Integer> state;
    private ICommandExecutor commandExecutor;

    public CommandExecutor(){
        state = new LinkedHashMap<>();
    }

    public void execute(String command) throws InvalidCommandException {
        setExecutor(command);
        commandExecutor.execute(command, state);
    }

    private void setExecutor(String command){
        if(true){
            this.commandExecutor =  new AssignmentCommandExecutor();
        }
    }

    public Map<String,Integer> getState(){
        return Collections.unmodifiableMap(state);
    }
}
