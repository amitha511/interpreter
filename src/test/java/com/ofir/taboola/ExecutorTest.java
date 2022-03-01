package com.ofir.taboola;

import com.ofir.taboola.exceptions.InvalidCommandException;
import com.ofir.taboola.exceptions.InvalidTokenException;
import com.ofir.taboola.executor.CommandExecutor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExecutorTest {

    @Test
    public void testState() {
        List<String> commands = Arrays.asList(
                "i = 5",
                "j = 2"
        );
        Map<String,Integer> state = execute(commands);
        assertEquals(true , state.containsKey("i"));
        assertEquals(true , state.containsKey("j"));
    }

    @Test
    public void testPreIncrement() {
        List<String> commands = Arrays.asList(
                "i = 5",
                "j = ++i"
        );
        Map<String,Integer> state = execute(commands);
        assertEquals(state.get("i") , 6);
        assertEquals(state.get("j") , 6);
    }

    @Test
    public void testPostIncrement() {
        List<String> commands = Arrays.asList(
                "i = 5",
                "j = i++"
        );
        Map<String, Integer> state = execute(commands);
        assertEquals(state.get("i") , 6);
        assertEquals(state.get("j") , 5);
    }

    @Test
    public void testPostIncrementChanging() {
        List<String> commands = Arrays.asList(
                "i = 5",
                "j = i++ + i"
        );
        Map<String, Integer> state = execute(commands);
        assertEquals(state.get("i") , 6);
        assertEquals(state.get("j") , 5 + 6);
    }

    @Test
    public void testPlusEqual() {
        List<String> commands = Arrays.asList(
                "i = 3",
                "j = 0",
                "j += i"
        );
        Map<String, Integer> state = execute(commands);
        assertEquals(state.get("i") , 3);
        assertEquals(state.get("j") , 3);
    }

    @Test
    public void testMul() {
        List<String> commands = Arrays.asList(
                "i = -3",
                "j = i * 5"
        );
        Map<String, Integer> state = execute(commands);
        assertEquals(state.get("i") , -3);
        assertEquals(state.get("j") , -15);
    }

    @Test
    public void testMulBeforeAdd() {
        List<String> commands = Arrays.asList(
                "i = 1",
                "j = i * 5 + i * 4"
        );
        Map<String, Integer> state = execute(commands);
        assertEquals(state.get("i") , 1);
        assertEquals(state.get("j") , 9);
    }

    private Map<String,Integer> execute(List<String> commands){
        CommandExecutor executor = new CommandExecutor();
        for(String command : commands){
            try {
                executor.execute(command);
            } catch (InvalidCommandException e) {
                return null;
            }
        }
        return executor.getState();
    }
}
