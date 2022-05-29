package com.amit.interpreter.executor;

import com.amit.interpreter.exceptions.InvalidCommandException;

import java.util.Map;

public interface ICommandExecutor {
    void execute(String command, Map<String, Integer> varsState) throws InvalidCommandException;
}
