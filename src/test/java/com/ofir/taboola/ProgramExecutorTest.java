package com.ofir.taboola;

import com.ofir.taboola.exceptions.ErrorMessages;
import com.ofir.taboola.exceptions.InvalidCommandException;
import com.ofir.taboola.executor.ProgramExecutor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ProgramExecutorTest {

    @Test
    public void testState() throws InvalidCommandException {
        List<String> commands = Arrays.asList(
                "i = 5",
                "j = 2"
        );
        Map<String,Integer> state = execute(commands);
        assertEquals(true , state.containsKey("i"));
        assertEquals(true , state.containsKey("j"));
    }

    @Test
    public void testUnmodifiableState() throws InvalidCommandException {
        List<String> commands = Arrays.asList(
                "i = 5",
                "j = 2"
        );
        Map<String,Integer> state = execute(commands);
        try{
            state.put("a" , 5);
        } catch (UnsupportedOperationException e){
            assertNotNull(e);
        }
    }

    @Test
    public void testPreIncrement() throws InvalidCommandException {
        List<String> commands = Arrays.asList(
                "i = 5",
                "j = ++i"
        );
        Map<String,Integer> state = execute(commands);
        assertEquals(state.get("i") , 6);
        assertEquals(state.get("j") , 6);
    }

    @Test
    public void testPostIncrement() throws InvalidCommandException {
        List<String> commands = Arrays.asList(
                "i = 5",
                "j = i++"
        );
        Map<String, Integer> state = execute(commands);
        assertEquals(state.get("i") , 6);
        assertEquals(state.get("j") , 5);
    }

    @Test
    public void testPostIncrementChanging() throws InvalidCommandException {
        List<String> commands = Arrays.asList(
                "i = 5",
                "j = i++ + i"
        );
        Map<String, Integer> state = execute(commands);
        assertEquals(state.get("i") , 6);
        assertEquals(state.get("j") , 5 + 6);
    }

    @Test
    public void testPlusEqual() throws InvalidCommandException {
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
    public void testMul() throws InvalidCommandException {
        List<String> commands = Arrays.asList(
                "i = -3",
                "j = i * 5"
        );
        Map<String, Integer> state = execute(commands);
        assertEquals(state.get("i") , -3);
        assertEquals(state.get("j") , -15);
    }

    @Test
    public void testMulBeforeAdd() throws InvalidCommandException {
        List<String> commands = Arrays.asList(
                "i = 1",
                "j = i * 5 + i * 4"
        );
        Map<String, Integer> state = execute(commands);
        assertEquals(state.get("i") , 1);
        assertEquals(state.get("j") , 9);
    }

    @Test
    public void testVariableNotDeclared() {
        List<String> commands = Arrays.asList(
                "i = 1",
                "j = k"
        );
        try {
            execute(commands);
        } catch (InvalidCommandException e) {
            assertNotNull(e);
            assertEquals(ErrorMessages.variableNotDeclared("k"), e.getMessage());
        }
    }

    @Test
    public void testVariableNotDeclaredPlusPlus() {
        List<String> commands = Arrays.asList(
                "i = 1",
                "j = k++"
        );
        try {
            execute(commands);
        } catch (InvalidCommandException e) {
            assertNotNull(e);
            assertEquals(ErrorMessages.variableNotDeclared("k"), e.getMessage());
        }
    }

    @Test
    public void testInvalidToken() {
        List<String> commands = Arrays.asList(
                "i = 1",
                "j = #"
        );

        try {
            execute(commands);
        } catch (InvalidCommandException e) {
            assertNotNull(e);
            assertEquals(ErrorMessages.invalidTokenMessage("#"), e.getMessage());
        }
    }

    private Map<String,Integer> execute(List<String> commands) throws InvalidCommandException {
        ProgramExecutor executor = new ProgramExecutor();
        for(String command : commands){
            executor.execute(command);
        }
        return executor.getState();
    }
}
