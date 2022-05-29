package com.amit.interpreter;

import com.amit.interpreter.validators.AssignmentTokenValidator;
import com.amit.interpreter.tokens.AbstractTokenAnalyzer;
import com.amit.interpreter.tokens.ArithmeticTokenAnalyzer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenAnalyzerTest {

    @Test
    public void testVariableNames() {
        AbstractTokenAnalyzer ta = new ArithmeticTokenAnalyzer();
        assertEquals(true, ta.isVariable("abc"));
        assertEquals(true, ta.isVariable("a_"));
        assertEquals(true, ta.isVariable("a1"));
        assertEquals(true, ta.isVariable("a_$"));
        assertEquals(false, ta.isVariable("_a"));

    }


    @Test
    public void testIncrementTokens() {
        AssignmentTokenValidator tv = new AssignmentTokenValidator();
        AbstractTokenAnalyzer ta = new ArithmeticTokenAnalyzer();

        assertEquals(true , tv.isIncrement("var++", ta));
        assertEquals(true , tv.isIncrement("++var", ta));
        assertEquals(false , tv.isIncrement("++", ta));
    }
}
