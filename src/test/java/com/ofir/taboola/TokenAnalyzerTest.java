package com.ofir.taboola;

import com.ofir.taboola.tokens.AbstractTokenAnalyzer;
import com.ofir.taboola.tokens.ArithmeticTokenAnalyzer;
import com.ofir.taboola.validators.AssignmentTokenValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
