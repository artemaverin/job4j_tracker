package ru.job4j.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FunctionCalculationTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = new FunctionCalculation().diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        List<Double> result = new FunctionCalculation().diapason(1, 4, x -> x * x + 1);
        List<Double> expected = Arrays.asList(2D, 5D, 10D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunctionThenExponentialResults() {
        List<Double> result = new FunctionCalculation().diapason(3, 5, x -> Math.pow(2, x));
        List<Double> expected = Arrays.asList(8D, 16D);
        assertThat(result, is(expected));
    }

}