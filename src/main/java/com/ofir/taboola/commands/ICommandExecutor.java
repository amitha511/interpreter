package com.ofir.taboola.commands;

import java.util.Map;

public interface ICommandExecutor {
    void execute(String command, Map<String, Integer> varsState);
}
