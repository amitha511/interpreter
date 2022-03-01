package com.ofir.taboola.executor;

import com.ofir.taboola.exceptions.InvalidCommandException;
import com.ofir.taboola.exceptions.InvalidTokenException;

import java.util.Map;

public interface ICommandExecutor {
    void execute(String command, Map<String, Integer> varsState) throws InvalidCommandException;
}
