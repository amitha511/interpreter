package com.ofir.taboola.executor;

import com.ofir.taboola.exceptions.InvalidCommandException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProgramExecutor {
    private Map<String,Integer> state;

    public ProgramExecutor(){
        state = new LinkedHashMap<>();
    }

    public void execute(String command) throws InvalidCommandException {
        commandExecutorFactory(command).execute(command, state);
    }

    /*
       we would like to support different command executors in the same program.
     */
    private ICommandExecutor commandExecutorFactory(String command){
        return new AssignmentCommandExecutor();
    }

    public Map<String,Integer> getState(){
        return Collections.unmodifiableMap(state);
    }
}
